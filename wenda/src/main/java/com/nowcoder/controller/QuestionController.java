package com.nowcoder.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nowcoder.async.EventModel;
import com.nowcoder.async.EventProducer;
import com.nowcoder.async.EventType;
import com.nowcoder.model.*;
import com.nowcoder.service.*;
import com.nowcoder.util.WendaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wenda on 2018/2/16.
 */
@Controller
public class QuestionController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    QuestionService questionService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    @Autowired
    FocusService focusService;

    @Autowired
    EventProducer eventProducer;

    @RequestMapping(value = "/question/{qid}", method = {RequestMethod.GET})
    public String questionDetail(Model model, @PathVariable("qid") int qid) {
        Question question = questionService.getById(qid);
        model.addAttribute("question", question);

        List<Comment> commentList = commentService.getCommentsByEntity(qid, EntityType.ENTITY_QUESTION);
        List<ViewObject> comments = new ArrayList<ViewObject>();
        for (Comment comment : commentList) {
            ViewObject vo = new ViewObject();
            vo.set("comment", comment);
            if (hostHolder.getUser() == null) {
                vo.set("liked", 0);
            } else {
                vo.set("liked", likeService.getLikeStatus(hostHolder.getUser().getId(), EntityType.ENTITY_COMMENT, comment.getId()));
            }
            vo.set("commentcount",commentService.getCommentCount(qid,EntityType.ENTITY_QUESTION));//返回评论数量
            vo.set("likeCount", likeService.getLikeCount(EntityType.ENTITY_COMMENT, comment.getId()));
            vo.set("user", userService.getUser(comment.getUserId()));
            comments.add(vo);
        }
        model.addAttribute("comments", comments);

        ViewObject vo=new ViewObject();
        vo.set("focuscount",focusService.getFocusCount(qid,EntityType.ENTITY_QUESTION));
        if (hostHolder.getUser() == null) {
            vo.set("isfocus", 0);
        }
            else{
            if (focusService.getFocusByUser(hostHolder.getUser().getId(), qid, EntityType.ENTITY_QUESTION) == null) {
                vo.set("isfocus", 0);
            } else {
                vo.set("isfocus", 1);
            }
        }
        model.addAttribute("focus", vo);
        return "detail";
    }

    @RequestMapping(value = "/question/add", method = {RequestMethod.POST})
    @ResponseBody
    public String addQuestion(@RequestParam("title") String title, @RequestParam("content") String content) {
        try {
            Question question = new Question();
            question.setContent(content);
            question.setCreatedDate(new Date());
            question.setTitle(title);
            if (hostHolder.getUser() == null) {
                question.setUserId(WendaUtil.ANONYMOUS_USERID);
                // return WendaUtil.getJSONString(999);
            } else {
                question.setUserId(hostHolder.getUser().getId());
            }
            if (questionService.addQuestion(question) > 0) {
                return WendaUtil.getJSONString(0);
            }
        } catch (Exception e) {
            logger.error("增加题目失败" + e.getMessage());
        }
        return WendaUtil.getJSONString(1, "失败");
    }
    @RequestMapping(value = "/question/addfocus",method={RequestMethod.POST})
    @ResponseBody
    public String addFocus(@RequestParam("questionId") int questionId)
    {
        try {
            if (hostHolder.getUser() == null) {
                //未登录则返回登录界面
                return WendaUtil.getJSONString(999);
            }
            Focus focus = new Focus();
            focus.setUserId(hostHolder.getUser().getId());
            focus.setEntityId(questionId);
            focus.setEntityType(EntityType.ENTITY_QUESTION);
            focus.setCreatedDate(new Date());

            int result=focusService.addFocus(focus);
            if(result<0){
                WendaUtil.getJSONString(1,"添加关注失败");
            }
        }catch (Exception e){
            logger.error("添加关注失败!"+e.getMessage());
            WendaUtil.getJSONString(1,"失败");
        }
        eventProducer.fireEvent(new EventModel().setActorId(hostHolder.getUser().getId())
                                                            .setEntityId(questionId)
                                                            .setEntityType(EntityType.ENTITY_QUESTION)
                                                            .setType(EventType.ADDFOCUS)
                                                            .setEntityOwnerId(questionService.getById(questionId).getUserId()));
            return WendaUtil.getJSONString(0,String.valueOf(focusService.getFocusCount(questionId,EntityType.ENTITY_QUESTION)));
    }

    @RequestMapping(value = "/question/delfocus",method={RequestMethod.POST})
    @ResponseBody
    public String delFocus(@RequestParam("questionId") int questionId)
    {
        try {
            if (hostHolder.getUser() == null) {
                //未登录则返回登录界面
                return WendaUtil.getJSONString(999);
            }
            Focus focus = new Focus();
            focus.setUserId(hostHolder.getUser().getId());
            focus.setEntityId(questionId);
            focus.setEntityType(EntityType.ENTITY_QUESTION);
            focus.setCreatedDate(new Date());

            int result=focusService.delFocus(focus);
            if(result<0){
                WendaUtil.getJSONString(1,"取消关注失败");
            }
        }catch (Exception e){
            logger.error("取消关注失败!"+e.getMessage());
            WendaUtil.getJSONString(1,"失败");
        }
        eventProducer.fireEvent(new EventModel().setActorId(hostHolder.getUser().getId())
                .setEntityId(questionId)
                .setEntityType(EntityType.ENTITY_QUESTION)
                .setType(EventType.DELFOCUS)
                .setEntityOwnerId(questionService.getById(questionId).getUserId()));
            return WendaUtil.getJSONString(0,String.valueOf(focusService.getFocusCount(questionId,EntityType.ENTITY_QUESTION)));
    }
    @RequestMapping(value = "/question/more",method={RequestMethod.POST})
    @ResponseBody
    public String more(@RequestParam("offset") int offset,@RequestParam("except") String except){
        if (hostHolder.getUser() == null) {
            //未登录则返回登录界面
            return WendaUtil.getJSONString(999);
        }
        List<ViewObject> vos;
        try {
            vos = getQuestions(0, offset, 5,except);
        }catch (Exception e)
        {
            logger.error("增加更多问题失败!"+e.getMessage());
            return WendaUtil.getJSONString(1);
        }
        JSONArray jsonArray=new JSONArray();
        for(ViewObject vo : vos){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("question",vo.get("question"));
            jsonObject.put("user",vo.get("user"));
            jsonArray.add(jsonObject);
        }
        String json_str=jsonArray.toString();
        return WendaUtil.getJSONString(0, json_str);
    }

    private List<ViewObject> getQuestions(int userId, int offset, int limit,String except) {
        List<Question> questionList = questionService.getLatestQuestionsWithExcept(userId, offset, limit,except);
        List<ViewObject> vos = new ArrayList<>();
        for (Question question : questionList) {
            ViewObject vo = new ViewObject();
            vo.set("question", question);
            vo.set("user", userService.getUser(question.getUserId()));
            vos.add(vo);
        }
        return vos;
    }

}

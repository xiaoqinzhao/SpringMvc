package com.nowcoder.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nowcoder.dao.QuestionDAO;
import com.nowcoder.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * Created by wenda on 2018/2/12.
 */
@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;

    @Autowired
    SensitiveService sensitiveService;

    public Question getById(int id) {
        return questionDAO.getById(id);
    }

    public int addQuestion(Question question) {
        question.setTitle(HtmlUtils.htmlEscape(question.getTitle()));
        question.setContent(HtmlUtils.htmlEscape(question.getContent()));
        // 敏感词过滤
        question.setTitle(sensitiveService.filter(question.getTitle()));
        question.setContent(sensitiveService.filter(question.getContent()));
        return questionDAO.addQuestion(question) > 0 ? question.getId() : 0;
    }

    public List<Question> getLatestQuestions(int userId, int offset, int limit) {
        return questionDAO.selectLatestQuestions(userId, offset, limit);
    }

    public List<Question> getLatestQuestionsWithExcept(int userId, int offset, int limit,String except) {
        return questionDAO.selectLatestQuestionsWithExcept(userId, offset, limit,except);
    }

    public PageInfo<Question> getQuestionsByPage(int userId,int currentPage, int pageSize)
    {
        //执行分页操作
            PageHelper.startPage(currentPage,pageSize);
        //查询数据库数据
        List<Question> questions=questionDAO.getAllQuestion();
        PageInfo<Question> questioninfo=new PageInfo<>(questions);

        return questioninfo;
    }
    public int updateCommentCount(int id, int count){
        return questionDAO.updateCommentCount(id, count);
    }
}

package com.example.newcoder.controller;

import com.example.newcoder.adapter.QuestionAdapter;
import com.example.newcoder.adapter.ScoreAdapter;
import com.example.newcoder.model.Answer;
import com.example.newcoder.model.HostHolder;
import com.example.newcoder.model.User;
import com.example.newcoder.service.QuestionService;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value={"/question"})
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @Autowired
    HostHolder hostHolder;

    @RequestMapping(value={"/home"},method = RequestMethod.GET)
    public String home(Model model,HttpServletRequest request,HttpServletResponse response)
    {
        if(hostHolder.getUser()==null)
        {
            String url="/question/loginpage";
                showMessqge(request, response, "身份失效，请重新登录！", url);
                return "";
        }

       List<QuestionAdapter> adapters=questionService.getRandomQuestionAndAnswer(10);
       questionService.handlestr(adapters);
        JSONArray json=JSONArray.fromObject(adapters);
        model.addAttribute("adapters",json);//向前端传入题目列表

        User user=hostHolder.getUser();
        model.addAttribute("username",user.getName());
        return "home";
    }

    @RequestMapping(value={"/check"},method = RequestMethod.POST)
    @ResponseBody
    public String check(HttpServletRequest request, HttpServletResponse response)
    {
        Map<Integer,Integer> map=new HashedMap();
        Enumeration em = request.getParameterNames();
        while (em.hasMoreElements()) {
        String name = (String) em.nextElement();
         String value = request.getParameter(name);
         map.put(Integer.valueOf(name),Integer.valueOf(value));//传入参数
    }
        int score=questionService.CheckAnswer(map);
        String url="/question/home";
        showMessqge(request,response,"you score is "+score,url);
        return "";
    }
    @RequestMapping(value={"/checkdetail"},method = RequestMethod.GET)
    @ResponseBody
    public String checkdetail(HttpServletRequest request, HttpServletResponse response)
    {
        String data=request.getParameter("data");
        System.out.println("data:"+data);

        List<Answer> answers=(List<Answer>)JSONArray.toList(JSONArray.fromObject(data),Answer.class);
        ScoreAdapter scores=questionService.CheckAnswerReturnDetail(answers);
        JSONObject json=JSONObject.fromObject(scores);
        System.out.println("json:"+json.toString());

        request.getSession(true);

        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setStatus(200);
        PrintWriter writer = null;
        OutputStream out=null;
        try {
            out = response.getOutputStream();
            writer=new PrintWriter(out);
            writer.print(json.toString());
            writer.flush();
            writer.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        return "";
    }
    @RequestMapping(value = {"/manager"})
    public String manager()
    {
        return "manager";
    }
    @RequestMapping(value = {"/add"},method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestParam("question") String question,
                      @RequestParam("answer") String answer,
                      @RequestParam("answers") String answers,
                      HttpServletResponse response, HttpServletRequest request)
    {
        boolean flag=questionService.AddQuestion(question,answer,answers);

        String url="/question/manager";
        if(flag) {
            showMessqge(request, response, "add Question successful", url);
        }else
        {
            showMessqge(request, response, "add Question failed", url);
        }

        return "";
    }
    /*
    message:显示的信息
    url:跳转的页面地址
     */
    public static void showMessqge(HttpServletRequest request,HttpServletResponse response,String message,String url)
    {
        request.getSession(true);

        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter writer = null;
        OutputStream out=null;
        try {
            out = response.getOutputStream();
            writer=new PrintWriter(out);
            writer.print("<script>alert('" + message+"');window.location.href='"
                        + url + "';</script>");
            writer.flush();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @RequestMapping("/head")
    public String head()
    {
        return "head";
    }
}

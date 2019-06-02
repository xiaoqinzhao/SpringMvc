package com.example.newcoder.controller;

import com.example.newcoder.adapter.QuestionAdapter;
import com.example.newcoder.dao.QuestionDao;
import com.example.newcoder.model.Book;
import com.example.newcoder.model.Question;
import com.example.newcoder.model.User;
import com.example.newcoder.service.QuestionService;
import com.example.newcoder.service.TicketService;
import com.example.newcoder.service.UserService;
import com.example.newcoder.service.wendaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value={"/question"})
public class indexcontroller {
    @Autowired
    wendaService wenda;
    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;
    @Autowired
    TicketService ticketService;
    @RequestMapping(path={"/"})
    @ResponseBody
    public String main(HttpSession session, HttpServletResponse response, HttpServletRequest request)
    {
        StringBuilder str=new StringBuilder();
        str.append("Hello World"+"<br>");
        str.append("msg:"+session.getAttribute("msg")+"<br>");
        response.addCookie(new Cookie("username","zhaoxiaoqin"));
        if(request.getCookies()!=null) {
            for (Cookie cookie : request.getCookies()) {
                str.append("cookie_name= "+cookie.getName()+" cookie_value= "+cookie.getValue()+"<br>");
            }
        }
        str.append("wenda:"+wenda.wenda("zhaoxiaoqin")+"<br>");
        return str.toString();
    }
    @RequestMapping(path = {"/index"})
    public String index(Model model)
    {
        List<String>  lst= Arrays.asList(new String[]{"Red","Blue","Yellow"});
        model.addAttribute("value1","vvv1");
        model.addAttribute("lst",lst);
        return "index";
    }
    @RequestMapping(path = {"/hacker"})
    public String hacker()
    {
        return "hacker";
    }
    @RequestMapping(path = {"/redirect/{code}"},method = RequestMethod.GET)
    public RedirectView redirect(@PathVariable("code") int code, HttpSession session)
    {
        session.setAttribute("msg","jump from redirect");
        RedirectView red=new RedirectView("/",true);
        if(code==301)
        {
            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        }
        return red;
    }
    @RequestMapping(path = {"/reg"},method=RequestMethod.POST)
    @ResponseBody
    public boolean reg(Model model,@RequestParam(name = "name",defaultValue = "",required = false) String name,
                       @RequestParam(name="password",defaultValue = "",required = false) String password,
                      HttpServletRequest request,HttpServletResponse response)
    {
        try {
            User user = new User();
            if (StringUtils.hasText(password) && StringUtils.hasText(name)) {
                return userService.RegUser(name, password);
            }
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }
    @RequestMapping(path = {"/regpage"},method=RequestMethod.GET)
    public String regpage()
    {
        return "reg";
    }

    @RequestMapping(path = {"/loginpage"},method=RequestMethod.GET)
    public String loginpage()
    {
        return "login";
    }

    @RequestMapping(path = {"/login"},method = RequestMethod.POST)
    @ResponseBody
    public boolean login(@RequestParam(name = "username",defaultValue = "",required = false) String username,
                           @RequestParam(name = "password",defaultValue = "",required = false) String password,
                           HttpServletResponse response)
    {
        System.out.println("微信小程序调用接口!!用户名:"+username+" 密码:"+password);
        boolean login=userService.login(username,password);
        if(login)
        {
            List<User> users=userService.selectbyname(username);
            User user;
            if(users.size()>0)
            {
                user=users.get(0);
                String ticket=ticketService.addTicketForUser(user);
                response.addCookie(new Cookie("ticket", ticket));
            }
            return true;
        }else
        {
            return false;
        }

    }
    @RequestMapping(path = {"/hintpage"},method=RequestMethod.GET)
    public String hintpage()
    {
        return "index";
    }
    @RequestMapping(path = {"/hint"},method=RequestMethod.GET)
    @ResponseBody
    public String hint(HttpServletRequest request)
    {
        //name数组
        String []name={"Anna","Brittany","Cinderella","Diana","Eva","Fiona","Gunda","Hege","Inga","Johanna","Kitty","Linda","Nina","Ophelia","Petunia","Amanda","Raquel","Cindy","Doris","Eve","Evita","Sunniva","Tove","Unni","Violet","Liza","Elizabeth","Ellen","Wenche","Vicky"};
        String returnstr="";
            String str=request.getParameter("q");
            if(str!=null&&str.length()>0) {
                List<User> users=userService.selectbynamelike(str);
                for (User user : users) {
                        returnstr += user.getName() + ",";
                }
            }
            if(returnstr=="")
            {
                return "not found";
            }else
            {
                returnstr=returnstr.substring(0,returnstr.length()-1);
                return returnstr;
            }
    }

    @RequestMapping(path = {"/jquery"},method=RequestMethod.GET)
    public String jquery()
    {
        return "jquery";
    }


    @RequestMapping(value={"/book"},method = RequestMethod.GET)
    public String book(Model model)
    {
        Book book=new Book();
        book.setCategory("123");
        model.addAttribute("book",book);
        return "book";
    }

    @Resource
    QuestionDao questionDao;

    @RequestMapping("/list")
    public String pageList(Model model,Integer currentPage, Integer pageSize){
        // 必须在mapper接口中的方法执行之前设置该分页信息
        currentPage = currentPage == null || currentPage<= 0 ? 1:currentPage;
        pageSize = pageSize == null || pageSize<= 0 ? 5:pageSize;
        //执行分页操作
        PageHelper.startPage(currentPage,pageSize);
        //查询数据库数据
        List<Question>  questions=questionDao.selectAll();
        handlestr(questions);
        List<QuestionAdapter> adapters=questionService.getQuestionAdapter(questions);
        PageInfo<Question>  questioninfo=new PageInfo<>(questions);
        PageInfo<QuestionAdapter>  pageinfo=new PageInfo<>(adapters);

        pageinfo.setEndRow(questioninfo.getEndRow());
        pageinfo.setFirstPage(questioninfo.getFirstPage());
        pageinfo.setHasNextPage(questioninfo.isHasNextPage());
        pageinfo.setHasPreviousPage(questioninfo.isHasPreviousPage());
        pageinfo.setIsFirstPage(questioninfo.isIsFirstPage());
        pageinfo.setIsLastPage(questioninfo.isIsLastPage());
        pageinfo.setStartRow(questioninfo.getStartRow());
        pageinfo.setLastPage(questioninfo.getLastPage());
        pageinfo.setNavigatepageNums(questioninfo.getNavigatepageNums());
        pageinfo.setNavigatePages(questioninfo.getNavigatePages());
        pageinfo.setNextPage(questioninfo.getNextPage());
        pageinfo.setPrePage(questioninfo.getPrePage());
        pageinfo.setSize(questioninfo.getSize());
        pageinfo.setOrderBy(questioninfo.getOrderBy());
        pageinfo.setPages(questioninfo.getPages());
        pageinfo.setPageSize(questioninfo.getPageSize());
        pageinfo.setPageNum(questioninfo.getPageNum());
        pageinfo.setTotal(questioninfo.getTotal());

        JSONObject json=new JSONObject().fromObject(pageinfo);
        System.out.println(json.toString());
        model.addAttribute("adapters",json.toString());
        return "list";
    }

    public static void handlestr(List<Question> questions)
    {
        for(Question question:questions)
        {
            question.setTitle(question.getTitle().replace('"','“'));
        }
    }

    @RequestMapping({"/logoff"})
    @ResponseBody
    public boolean LogOff(HttpServletRequest request,HttpServletResponse response) {
        String ticket="";
        if(request.getCookies()!=null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("ticket")) {
                    ticket = cookie.getValue();
                }
            }
        }
        return ticketService.Logoff(ticket,response);
    }
}

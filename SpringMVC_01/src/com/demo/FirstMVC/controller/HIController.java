package com.demo.FirstMVC.controller;

import com.demo.FirstMVC.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = {"/hi"})
public class HIController {
    @RequestMapping(value = {"/say"},method = RequestMethod.GET )

    public String say(Model model)
    {
        User user=new User();
        user.setId(123);
        user.setName("zhaoxiaoqin");
        user.setPassword("031001");
        user.setSalt("sd122");
        //JSONObject json = JSONObject.fromObject(user);

        model.addAttribute("name","zhaoxiaoqin");
        model.addAttribute("url","www.baidu.com");
        model.addAttribute("user",user);
        return "say.jsp";
    }
    @RequestMapping(value={"/hint"},method = RequestMethod.GET)
    @ResponseBody
    public String hint()
    {
        return "zhao";
    }
    @RequestMapping(value={"/home"},method = RequestMethod.GET)
    public String home()
    {
        return "getname.html";
    }
}

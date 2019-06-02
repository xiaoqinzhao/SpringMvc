package cn.itcast.controller;

import cn.itcast.domain.Account;
import cn.itcast.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/findAll")
    public String findAll(Model model){
        List<Account> list=accountService.findAll();
        model.addAttribute("list",list);
        return "list";
    }
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestParam("name")String name,@RequestParam("money")Double money){
        Account account=new Account();
        account.setName(name);
        account.setMoney(money);
        accountService.saveAccount(account);
        return "success";
    }
}

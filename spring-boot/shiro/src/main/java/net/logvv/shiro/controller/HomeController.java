package net.logvv.shiro.controller;

import net.logvv.shiro.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    private AccountService accountService;
	
    @RequestMapping(value="/index",method=RequestMethod.GET)
    public String index(){
       return "/index";
    }
   
    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(){
       return "login";
    }
}

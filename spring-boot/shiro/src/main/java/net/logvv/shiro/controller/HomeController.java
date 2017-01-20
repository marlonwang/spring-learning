package net.logvv.shiro.controller;

import net.logvv.shiro.model.Account;
import net.logvv.shiro.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private AccountService accountService;
	
    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String index(@RequestParam(value = "name",required = false,defaultValue = "world")String name,
                        Model model)
    {
        model.addAttribute("name",name);
        return "index";
    }
   
    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(@RequestParam("username")String userName, @RequestParam("password")String password,
                        Model model)
    {
        LOGGER.info("user login...");
        String msg = accountService.login(userName,password);
        //session.setAttribute("token",msg);
        model.addAttribute("msg",msg);

        return "login";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String profile(@RequestParam(value = "firstname",required = false,defaultValue = "alex")String firstname,
                           @RequestParam(value = "lastname",required = false,defaultValue = "wang")String lastname,
                           Model model)
    {
        Account account = accountService.fetchAccount(firstname,lastname);

        model.addAttribute("account",account);
        return "index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(@RequestHeader("token")String token)
    {
        accountService.logout(token);
        return "index";
    }

}

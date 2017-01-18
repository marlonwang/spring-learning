package net.logvv.session.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by marlon on 2017/1/18.
 */
@RestController
public class AccountController {

    @RequestMapping(value = "/login", produces = "application/json")
    public Map<String, String> hello(@RequestParam("name")String name,
                                     HttpServletRequest request)
    {
        HashMap<String, String> result = new HashMap<>();

        HttpSession sessison = request.getSession();
        if(request.getSession(false) == null) {
            System.out.println("session expired.");
            sessison.setAttribute("accessToken",name);
        }else {
            System.out.println("session active.");
        }
        result.put("username", name);
        return result;
    }

    @RequestMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpSession session) {
        session.invalidate();
        System.out.println("logout");
    }

    @RequestMapping("/action")
    public String doSomething(@RequestHeader("accessToken")String accessToken,
                              HttpSession session)
    {
        if(accessToken.equals(session.getAttribute("accessToken")))
        {
            System.out.println("do something...");
        }else {
            System.out.println(session.getAttribute("accessToken"));
            System.out.println("expired accessToken... login again.");
            return "error2";
        }

        return "action done";
    }

    @RequestMapping(value = "/error2")
    public String error()
    {
        return "error page";
    }

}

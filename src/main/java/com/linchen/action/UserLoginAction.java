package com.linchen.action;

import com.linchen.bo.UserBoI;
import com.linchen.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserLoginAction {
    @Autowired
    private UserBoI userBoI;

    /*用户登录*/
    @RequestMapping(value = "/login.html",method = RequestMethod.POST)
    public String login(@RequestParam("userCode") String userCode,@RequestParam("userPassword") String password,
                        HttpSession session, HttpServletRequest request) {
        User user = userBoI.login(userCode, password);
        if (user != null) {
            session.setAttribute("userSession", user);
            return "frame";
        } else {
            request.setAttribute("error","用户名或密码错误");
            return "forward:/login.jsp";
        }
    }

    /*用户退出*/
    @RequestMapping("/jsp/logout.do")
    public String logout(HttpSession session){
        session.invalidate();
        return "forward:/login.jsp";
    }
}

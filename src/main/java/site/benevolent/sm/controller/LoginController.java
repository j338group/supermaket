package site.benevolent.sm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import site.benevolent.sm.pojo.Users;
import site.benevolent.sm.service.UsersBo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UsersBo usersBo;

    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public String login(String userCode, String userPassword, HttpSession session, HttpServletRequest request) {
        Users login = usersBo.login(userCode, userPassword);
        if (login != null) {
            session.setAttribute("userSession", login);
            return "frame";
        } else {
            request.setAttribute("error", "用户名或者密码错误！");
            return "forward:/login.jsp";
        }
    }

    @RequestMapping("/jsp/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "forward:/login.jsp";
    }
}

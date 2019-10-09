package cn.smbms.controller;

import cn.smbms.pojo.User;
import cn.smbms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * description:
 * Created by Ray on 2019-09-23
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;



    @RequestMapping(value = "/login.html",method = RequestMethod.POST)
    public String login(String userCode, String userPassword, HttpSession session,HttpServletRequest request){
        User user = userService.login(userCode, userPassword);
        if (user != null) {
            System.out.println("登录成功！");
            session.setAttribute("userSession",user);
            return "frame";
        }else {
            request.setAttribute("error","用户名或密码错误！");
            return "forward:/login.jsp";
        }
    }
    @RequestMapping("/jsp/logout")
    public String logout(HttpSession session ){
        session.invalidate();
        return "forward:/login.jsp";
    }
}

package cn.smbms.controller;

import cn.smbms.pojo.User;
import cn.smbms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login.do",method = RequestMethod.GET)
    @ResponseBody
    public String login(String userCode,String userPassword){
      User user =userService.login(userCode,userPassword);
      if (user!=null){
          System.out.println("登录成功！");
      }
      return user.getUserName();
    }
}

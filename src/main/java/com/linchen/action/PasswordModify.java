package com.linchen.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linchen.bo.UserBoI;
import com.linchen.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PasswordModify {
    @Autowired
    private UserBoI userBoI;

    /*跳转修改密码页面*/
    @RequestMapping(value = "/jsp/pwdmodify.html")
    public String updatePassword() {
        return "pwdmodify";
    }

    /*验证原始密码*/
    @RequestMapping("/jsp/password.html")
    @ResponseBody
    public String passwordView(HttpServletRequest request, @RequestParam("oldpassword") String oldPassword) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        String userPassword = user.getUserPassword();

        Map<String, String> passwordStr = new HashMap<>();
        if (user == null) {
            passwordStr.put("result", "sessionerror");
        } else if (Integer.parseInt(oldPassword) == Integer.parseInt(userPassword)) {
            passwordStr.put("result", "true");
        } else if (oldPassword.equals(userPassword)) {
            passwordStr.put("result", "false");
        } else {
            passwordStr.put("result", "error");
        }

        String confirm = null;
        try {
            confirm = new ObjectMapper().writeValueAsString(passwordStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return confirm;
    }

    /*执行修改*/
    @RequestMapping(value = "/jsp/updatepassword.html", method = RequestMethod.POST)
    public String doUpdatePassword(Model model, HttpServletRequest request, @RequestParam("rnewpassword") String newPassword) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        Integer id = user.getId().intValue();

        userBoI.modifyPassword(id, newPassword);
        model.addAttribute("message", "OK!");
        return "pwdmodify";
    }
}

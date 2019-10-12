package cn.smbms.controller;

import cn.smbms.pojo.User;
import cn.smbms.service.UserService;
import cn.smbms.vo.UserVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description:
 * Created by Ray on 2019-10-09
 */
@Controller
public class DemoAjaxController {
    @Autowired
    private UserService userService;
    @RequestMapping("/getuser/{id}")
    @ResponseBody
    public UserVo getUser(@PathVariable String id) throws JsonProcessingException {
        UserVo user = userService.findUserById(id);

//        return "{\"name\":\"tom\"}";
//        String userJson = JSONObject.toJSONString(user);
//        System.out.println(userJson);
//        String s = new ObjectMapper().writeValueAsString(user);
//        System.out.println(s);
        return user;
    }

    @RequestMapping("/senduser")
    @ResponseBody
    public UserVo sendUser(String uname,String password,String id) throws JsonProcessingException {
        System.out.println(id);
        System.out.println(uname);
        System.out.println(password);
        UserVo user = userService.findUserById(id);

        return user;
    }
}

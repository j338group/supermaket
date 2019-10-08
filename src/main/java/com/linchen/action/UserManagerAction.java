package com.linchen.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linchen.bo.RoleBoI;
import com.linchen.bo.UserBoI;
import com.linchen.dto.Role;
import com.linchen.dto.User;
import com.linchen.ro.PageInfo;
import com.linchen.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jsp")
public class UserManagerAction {
    @Autowired
    private UserBoI userBoI;
    @Autowired
    private RoleBoI roleBoI;

    /*用户列表*/
    @RequestMapping(value = "/userlist.html", method = RequestMethod.GET)
    public String userList(Model model, @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
                           @RequestParam(value = "queryname", required = false) String queryUserName,
                           @RequestParam(value = "queryUserRole", required = false) Integer roleId) {

        System.out.println(queryUserName);
        int pageNo = 1;
        if (pageIndex != null) {
            pageNo = pageIndex;
        }
        int amount = userBoI.countUser();
        PageInfo page = PageInfo.of(pageNo, 5, amount);

        List<UserVo> userList = userBoI.queryUserList(page.getFirst(), page.getPageSize(), queryUserName, roleId);
        List<Role> roleList = roleBoI.queryRoleList();

        model.addAttribute("roleList", roleList);
        model.addAttribute("userList", userList);
        model.addAttribute(page);

        model.addAttribute("queryUserName", queryUserName);
        model.addAttribute("queryUserRole", roleId);
        return "userlist";
    }

    //用户添加
    /*角色列表*/
    @RequestMapping(value = "/rolelist.html", produces = {"text/html;charset=UTF-8;"})
    @ResponseBody
    public String roleList() {
        List<Role> roles = roleBoI.queryRoleList();
        String s = null;
        try {
            s = new ObjectMapper().writeValueAsString(roles);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }

    @RequestMapping(value = "/useradd.html", method = RequestMethod.GET)
    public String userAdd() {
        return "useradd";
    }

    @RequestMapping("/userexist/{userCode}")
    @ResponseBody
    public Map<String, String> userExist(@PathVariable("userCode") String code) {
        Boolean b = userBoI.queryUserCode(code);
        Map<String, String> map = new HashMap<>();

        if (b) {
            map.put("userCode", "exist");
        } else {
            map.put("userCode", "");
        }
        return map;
    }

    @RequestMapping(value = "/adduser.html", method = RequestMethod.POST)
    public String doUserAdd(User user) {
        userBoI.add(user);
        return "redirect:/jsp/userlist.html";
    }

    /*查看用户*/
    @RequestMapping(value = "/userview.html", method = RequestMethod.GET)
    public String view(Model model, @RequestParam("uid") Long id) {
        UserVo user = userBoI.queryUser(id);
        model.addAttribute("user", user);
        return "userview";
    }

    /*删除用户*/
    @RequestMapping(value = "/deleteuser.html", method = RequestMethod.GET)
    @ResponseBody
    public String deleteUser(@RequestParam("uid") Long id) {
        String b = userBoI.deleteUser(id);
        Map<String, String> result = new HashMap<>();
        result.put("delResult", b);

        String str = null;

        try {
            str = new ObjectMapper().writeValueAsString(result);//将对象转换为json串
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /*修改用户*/
    @RequestMapping(value = "/updateuser.html", method = RequestMethod.GET)
    public String updateUser(Model model, @RequestParam("uid") Long id) {
        UserVo user = userBoI.queryUser(id);

        model.addAttribute("user", user);
        return "usermodify";
    }

    @RequestMapping(value = "/doupdateuser.html", method = RequestMethod.POST)
    public String doUpdateUser(User user) {
        userBoI.updateUser(user);
        return "redirect:/jsp/userlist.html";
    }

}

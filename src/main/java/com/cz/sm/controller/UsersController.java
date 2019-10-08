package com.cz.sm.controller;

import com.cz.sm.pojo.Role;
import com.cz.sm.pojo.Users;
import com.cz.sm.pojo.UsersVo;
import com.cz.sm.service.RoleBo;
import com.cz.sm.service.UsersBo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jsp/user")
public class UsersController {
    @Autowired
    private UsersBo usersBo;
    @Autowired
    private RoleBo roleBo;

    @RequestMapping("/userlist.html")
    public String queryUserList(Model model,
           @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
           @RequestParam(value = "queryname", required = false) String queryname,
           @RequestParam(value = "queryUserRole", required = false) Integer roleId) {
        int totalCount = usersBo.usersCount();
        int pageSize = 5;
        int totalPageCount = totalCount % 5 == 0 ? totalCount / 5 : totalCount / 5 + 1;
        int currentPageNo = 1;
        if (pageIndex != null) {
            currentPageNo = pageIndex;
        }
        List<Users> userList = usersBo.queryByPage(queryname, roleId, currentPageNo, pageSize);
        List<Role> roleList = roleBo.queryAllRole();
        //分页查询出来的用户列表，单页的，小于等于pageSize
        model.addAttribute("userList", userList);
        //总的页数，若总条数除以pageSize为整就为商，否则加1
        model.addAttribute("totalPageCount", totalPageCount);
        //总条数
        model.addAttribute("totalCount", totalCount);
        //当前页码
        model.addAttribute("currentPageNo", currentPageNo);
        //供查询使用的roleList
        model.addAttribute("roleList", roleList);
        //回显
        model.addAttribute("queryUserName", queryname);
        //回显
        model.addAttribute("queryUserRole", roleId);
        return "userlist";
    }

    @RequestMapping("/userview/{userId}")
    public String queryUserInfo(@PathVariable("userId") String userId, Model model) {
        Users userVo = usersBo.queryById(Long.parseLong(userId));
        model.addAttribute("user", userVo);
        return "userview";
    }

    @RequestMapping("/deluser/{userId}")
    @ResponseBody
    public String delUser(@PathVariable("userId") String userId) {
        Boolean b = usersBo.delUserById(Long.parseLong(userId));
        Map<String, Object> result = new HashMap<>();
        result.put("delResult", b.toString());
        String resultJson = null;
        try {
            resultJson = new ObjectMapper().writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultJson;
    }

    /**
     * 修改用户
     *
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("/usermodify/{userId}")
    public String modifyUser(@PathVariable("userId") String userId, Model model) {
        UsersVo userVo = usersBo.queryByIdModify(Long.parseLong(userId));
        model.addAttribute("user", userVo);
        return "usermodify";
    }

    @RequestMapping("/user.save")
    public String usersModify(String uid, String userName, String gender, String birthday, String phone, String address, String userRole) {
        UsersVo users = new UsersVo();
        users.setId(Long.parseLong(uid));
        users.setUserName(userName);
        users.setGender(Integer.parseInt(gender));
        users.setBirthday(Date.valueOf(birthday));
        users.setPhone(phone);
        users.setAddress(address);
        users.setUserRole(Long.parseLong(userRole));
        usersBo.updateById(users);
        return "redirect:/jsp/user/userlist.html";
    }


    @RequestMapping("/rolelist")
    @ResponseBody
    public List queryRoleList() {
        List<Role> roles = roleBo.queryAllRole();
        return roles;
    }

    @RequestMapping("/add.html")
    public String doAddUser() {
        return "useradd";
    }

    @RequestMapping("/user.add")
    public String saveUser(String userCode, String userName, String userPassword, String gender, String birthday, String phone, String address, String userRole, String createdBy) {
        Users users = new Users(userCode, userName, userPassword, Integer.valueOf(gender), Date.valueOf(birthday), phone, address, Integer.parseInt(userRole), Long.parseLong(createdBy));
        System.out.println(users);
        usersBo.addUser(users);
        System.out.println("SUCCESS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "redirect:/jsp/user/userlist.html";
    }

    @RequestMapping("/ucexist/{userCode}")
    @ResponseBody
    public Users checkUserCode(@PathVariable("userCode") String userCode) {
        Users users = usersBo.checkUserCode(userCode);
        return users;
    }

    @RequestMapping("/pwdmodify.do")
    public String pwdModify() {
        return "pwdmodify";
    }

    @RequestMapping("/pwdcheck/{oldpassword}")
    @ResponseBody
    public Users pwdCheck(@PathVariable("oldpassword") String oldpassword) {
        return usersBo.checkPwd(oldpassword);
    }

    @RequestMapping("/pwd.update")
    public String updatePwd(String newpassword, HttpSession session) {
        Users users = (Users) session.getAttribute("userSession");
        String userCode = users.getUserCode();
        usersBo.updatePwd(newpassword, userCode);
        System.out.println("密码更新成功！！！！！！！！！！！！！！！！！");
        return "frame";
    }

}

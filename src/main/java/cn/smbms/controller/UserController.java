package cn.smbms.controller;


import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.RoleService;
import cn.smbms.service.UserService;
import cn.smbms.vo.UserVo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 * Created by Ray on 2019-09-24
 */
@Controller
@RequestMapping("/jsp/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/userlist.html")
    public String queryUserList(Model model,
                                @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
                                @RequestParam(value = "queryname", required = false) String queryname,
                                @RequestParam(value = "queryUserRole", required = false) Integer roleId) {
        //处理分页数据
        int totalCount = userService.queryUserCount();
        int pageSize = 5;
        int totalPageCount = totalCount % 5 == 0 ? totalCount / 5 : totalCount / 5 + 1;
        int currentPageNo = 1;
        if (pageIndex != null) {
            currentPageNo = pageIndex;
        }
        //调用业务层功能查询用户列表
        List<UserVo> userList = userService.queryUserList(queryname, roleId, currentPageNo, pageSize);
        //查询角色列表
        List<Role> roleList = roleService.queryRoleList();

        //封装返回给前端的数据
        model.addAttribute("userList", userList);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);
        model.addAttribute("roleList", roleList);
        model.addAttribute("queryUserName", queryname);
        model.addAttribute("queryUserRole", roleId);

        return "userlist";
    }

    @RequestMapping("userview/{userId}")
    public String queryUserInfo(@PathVariable("userId") String userId, Model model) {
        UserVo userVo = userService.findUserById(userId);
        model.addAttribute("user", userVo);
        return "userview";
    }
    @RequestMapping("/deluser/{userId}")
    @ResponseBody
    public String delUser(@PathVariable("userId") String userId){
        Boolean b=userService.delUserById(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("delResult",b.toString());
        String resultJson=null;
        try {
            resultJson = new ObjectMapper().writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultJson;
    }
    @RequestMapping("/usermodify/{userId}")
    public String modifyUser(@PathVariable("userId") String userId,Model model){
        UserVo userVo = userService.findUserById(userId);
        model.addAttribute("user",userVo );
        return "usermodify";
    }
    @RequestMapping("/rolelist")
    @ResponseBody
    public List queryRoleList(){
        List<Role> roles = roleService.queryRoleList();
        return roles;
    }
    @RequestMapping("/add.html")
    public String doAddUser(){
        return "useradd";
    }
    @RequestMapping("/adduser")
    public String addUser(User user, HttpSession session){
        System.out.println("--"+user);
        User loginuser = (User) session.getAttribute("userSession");
        Boolean b=userService.addUser(user,loginuser.getId());
//TODO 跳转页面
        return "useradd";
    }
    @RequestMapping("/ucexist/{userCode}")
    @ResponseBody
    public Map<String,String> userCodeExist(@PathVariable("userCode") String userCode) {
        Boolean have = userService.findUserByUserCode(userCode);

        Map<String, String> map = new HashMap<>();
        if (have) {
            map.put("userCode", "exist");
        } else {
            map.put("userCode", "");
        }
        return map;
    }


    @RequestMapping("/pwdmodify.html")
    public String updateUser(){
        return "pwdmodify";
    }

    @RequestMapping("/user.do")
    public String updateUser(User user,Long uid){
        System.out.println(user+"-----------------------");
        System.out.println(uid);
        userService.updateUser(user,uid);
        return "usermodify";
    }
    @RequestMapping("/pwd/")
    @ResponseBody
    public String oldpwd() throws JsonProcessingException {
        String s="error";
        Map<String,String> result=new HashMap<>();
        result.put("result",s);

        String s1 = new ObjectMapper().writeValueAsString(result);

        return s1;
    }
    //验证旧密码对不对pwdmodify.js
    @RequestMapping("/pwd/{oldpassword}")
    @ResponseBody
    public String oldpwd(@PathVariable(value = "oldpassword",required = false)String oldpassword,HttpSession session){
        User loginUser=(User)session.getAttribute("userSession");
        String s=null;
        System.out.println(loginUser.getUserPassword());
        System.out.println(oldpassword.getClass()+"-----------------------------------------------");
        System.out.println(oldpassword==loginUser.getUserPassword());
        System.out.println(loginUser.getUserPassword().getClass());
        System.out.println(loginUser.getUserPassword()+"..............................................");
        if (loginUser.getUserPassword().equals(oldpassword)){
            s="true";
        }else if (session==null){
            s="sessionerror";
        }else if (!loginUser.getUserPassword().equals(oldpassword)){
            s="false";
        }
        Map<String,String> result=new HashMap<>();
        result.put("result",s);
        String s1=null;
        try {
            s1=new ObjectMapper().writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s1;
    }
    //pwdmodify.jsp修改密码
    @RequestMapping("updatepwd")
    public String updatepwd(String newpassword,HttpSession session){
        User loginUser=(User)session.getAttribute("userSession");
        int i=userService.updateUserPassword(newpassword,loginUser);

        return "forward:/login.jsp";
    }
}

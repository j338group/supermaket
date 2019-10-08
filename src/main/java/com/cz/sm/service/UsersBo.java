package com.cz.sm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cz.sm.dao.UsersMapper;
import com.cz.sm.pojo.Users;
import com.cz.sm.pojo.UsersVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersBo {
    @Autowired
    private UsersMapper usersMapper;

    //登录业务
    public Users login(String userCode, String password) {
        return usersMapper.login(userCode, password);
    }

    public int usersCount() {
        int count = usersMapper.usersCount();
        return count;
    }

    public List<Users> queryByPage(String queryName, Integer roleId, int currentPageNo, int pageSize) {
        int startIndex = (currentPageNo - 1) * pageSize;
        Map<String, Object> param = new HashMap<>();
        param.put("startIndex", startIndex);
        param.put("pageSize", pageSize);
        param.put("queryName", queryName);
        param.put("roleId", roleId);
        return usersMapper.findByPage(param);
    }

    public Users queryById(Long id) {
        return usersMapper.findById(id);
    }

    public UsersVo queryByIdModify(Long id) {
        return usersMapper.findByIdModify(id);
    }

    public int updateById(UsersVo usersVo) {
        return usersMapper.updateById(usersVo);
    }

    public Boolean delUserById(Long userId) {
        return usersMapper.deleteById(userId);
    }

    public int addUser(Users users) {
        return usersMapper.saveUser(users);
    }

    public Users checkUserCode(String userCode) {
        return usersMapper.checkUserCode(userCode);
    }

    public Users checkPwd(String olePassword) {
        return usersMapper.checkPwd(olePassword);
    }
    public int updatePwd(String newpassword,String userCode){
        return usersMapper.updatePwd(newpassword,userCode);
    }
}

package com.linchen.bo;

import com.linchen.dao.UserMapper;
import com.linchen.dto.User;
import com.linchen.dto.UserExample;
import com.linchen.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class UserBo implements UserBoI {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String userCode, String password) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserCodeEqualTo(userCode);
        criteria.andUserPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<UserVo> queryUserList(int first, int pageSize, String queryUserName, Integer roleId) {
        return userMapper.findUserList(first, pageSize, queryUserName, roleId);
    }

    @Override
    public int countUser() {
        return (int) userMapper.countByExample(new UserExample());
    }

    @Override
    public int modifyPassword(Integer id, String password) {
        return userMapper.updatePassword(id, password);
    }

    @Override
    public int add(User user) {
        user.setCreationDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        user.setCreatedBy(1L);
        return userMapper.insert(user);
    }

    @Override
    public UserVo queryUser(Long id) {
        return userMapper.findUserById(id);
    }

    @Override
    public String deleteUser(Long id) {
        Integer num = userMapper.deleteByPrimaryKey(id);
        if (num == null) {
            return "notexist";
        } else if (num > 0) {
            return "true";
        } else {
            return "false";
        }
    }

    @Override
    public Boolean queryUserCode(String code) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserCodeEqualTo(code);
        List<User> users = userMapper.selectByExample(example);
        if (users.size()>0){
            return true;
        }
        return  false;
    }

    @Override
    public int updateUser(User user) {
        user.setModifyDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        int i = userMapper.updateUserById(user);
        return i;
    }
}

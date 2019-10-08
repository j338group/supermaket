package com.linchen.bo;

import com.linchen.dto.User;
import com.linchen.vo.UserVo;

import java.util.List;

public interface UserBoI {
    User login(String userCode,String password);

    List<UserVo> queryUserList(int first,int pageSize,String queryUserName,Integer roleId);

    int countUser();

    int modifyPassword(Integer id,String password);

    int add(User user);

    UserVo queryUser(Long id);

    String deleteUser(Long id);

    Boolean queryUserCode(String code);

    int updateUser(User user);
}

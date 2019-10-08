package com.linchen.dao;

import com.linchen.dto.User;
import com.linchen.dto.UserExample;
import java.util.List;

import com.linchen.vo.UserVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<UserVo> findUserList(@Param("first") int first,@Param("pageSize") int pageSize,@Param("queryUserName") String queryUserName,@Param("roleId") Integer roleId);

    int updatePassword(@Param("id")Integer id,@Param("password") String password);

    UserVo findUserById(Long id);

    int updateUserById(User user);
}
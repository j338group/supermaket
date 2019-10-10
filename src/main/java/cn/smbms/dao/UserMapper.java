package cn.smbms.dao;

import cn.smbms.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.UserException;

import java.util.List;

public interface UserMapper {
    long countByExample(UserException example);
    int deleteByExample(UserException example);
    int deleteByPrimaryKey(Long id);
    int insert(User record);
    int insertSelective(User record);
    List<User> selectByExample(UserException example);
    User selectByPrimaryKey(Long id);
    int updateByExampleSelective(@Param("record")User record,@Param("example") UserExample example);
    int updateByExample(@Param("record") User record,@Param("example") UserException example);
    int updateByPrimaryKeySelective(User record);
    int updateByPrimaryKey(User record);
}

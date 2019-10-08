package site.benevolent.sm.dao;

import org.apache.ibatis.annotations.Param;
import site.benevolent.sm.pojo.Users;
import site.benevolent.sm.pojo.UsersVo;

import java.util.List;
import java.util.Map;

public interface UsersMapper {
    Users login(@Param("userCode") String userCode, @Param("userPassword") String userPassword);

    int usersCount();

    List<Users> findByPage(Map<String, Object> param);

    Users findById(Long id);

    UsersVo findByIdModify(Long id);

    int updateById(UsersVo usersVo);

    Boolean deleteById(Long userId);

    int saveUser(Users users);

    Users checkUserCode(String userCode);

    Users checkPwd(String oldPassword);

    int updatePwd(@Param("pwd") String newpassword, @Param("code") String userCode);
}

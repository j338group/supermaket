package cn.smbms.service;

import cn.smbms.pojo.User;
import cn.smbms.vo.UserVo;

import java.util.List;

/**
 * description:
 * Created by Ray on 2019-09-23
 */
public interface UserService {
    User login(String userCode, String password);

    //    List<UserVo> queryUserList();
    int queryUserCount();

    List<UserVo> queryUserList(String queryName, Integer roleId, int currentPageNo, int pageSize);

    UserVo findUserById(String userId);

    boolean delUserById(String userId);

    boolean addUser(User user, Long id);

    boolean findUserByUserCode(String userCode);

    void updateUser(User user, Long uid);

    int updateUserPassword(String newpassword, User user);
}

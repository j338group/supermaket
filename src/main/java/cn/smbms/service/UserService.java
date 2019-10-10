package cn.smbms.service;

import cn.smbms.pojo.User;

public interface UserService {
    User login(String userCode,String password);
}

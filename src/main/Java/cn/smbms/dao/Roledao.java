package cn.smbms.dao;

import cn.smbms.pojo.Role;

import java.util.List;

public interface Roledao {
    void save(Role r);

    int fiaddelete(int id);

    void fiadset(Role r);

    Role fiad(int id);

    List<Role> fiadlist();
}

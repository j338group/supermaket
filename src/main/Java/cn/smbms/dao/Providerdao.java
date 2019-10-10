package cn.smbms.dao;

import cn.smbms.pojo.Provider;

import java.util.List;

public interface Providerdao {
    Provider find(long id);

    List<Provider> findlist();

    Integer findset(Provider p);

    int finddelete(int id);

    void save(Provider p);
}

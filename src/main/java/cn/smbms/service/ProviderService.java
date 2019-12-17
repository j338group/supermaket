package cn.smbms.service;

import cn.smbms.pojo.Provider;

import java.util.List;

public interface ProviderService {
    List<Provider> queryProviderList(String proCode, String proName);

    Provider queryById(String proId);

    int updateProvider(Provider provider);

    String deleteProvider(String proId);

    int addProvider(Provider provider);

    List<Provider> queryProvider1List();
}

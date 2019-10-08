package cn.smbms.service;


import cn.smbms.pojo.Provider;

import java.util.List;

public interface ProviderService {
    List<Provider> queryProvideList1();

    List<Provider> queryProvideList(String queryProCode, String queryProName);

    Provider findProById(String proId);

    boolean delProById(String proId);

    boolean addPro(Provider provider, Long id);

    boolean findUserByProCode(String ProCode);

}

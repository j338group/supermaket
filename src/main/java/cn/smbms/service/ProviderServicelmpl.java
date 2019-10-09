package cn.smbms.service;

import cn.smbms.dao.ProviderMapper;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.ProviderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServicelmpl implements ProviderService{
    @Autowired
    private ProviderMapper providerMapper;
    @Override
    public List<Provider> queryProviderList(){
        ProviderExample example = new ProviderExample();
        return providerMapper.selectByExample(example);
    }
}

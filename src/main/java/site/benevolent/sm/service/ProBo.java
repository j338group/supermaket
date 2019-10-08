package site.benevolent.sm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.benevolent.sm.dao.ProviderMapper;
import site.benevolent.sm.pojo.Providers;

import java.util.List;

@Service
public class ProBo {
    @Autowired
    private ProviderMapper providerMapper;
    public List<Providers> queryAllPro(String queryProCode,String queryProName){
        List<Providers> proList = providerMapper.findAllPro(queryProCode, queryProName);
        return proList;
    }
    public Providers queryById(Long proId){
        return providerMapper.findById(proId);
    }
    public int updateById(Providers provider){
        return providerMapper.updateById(provider);
    }
    public Boolean deleteById(Long proId){
        return providerMapper.delById(proId);
    }
    public int savePro(Providers pro){
        return providerMapper.saveByPro(pro);
    }
}

package com.cz.sm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cz.sm.dao.ProviderMapper;
import com.cz.sm.pojo.Providers;

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

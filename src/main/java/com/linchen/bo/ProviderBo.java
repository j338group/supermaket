package com.linchen.bo;

import com.linchen.dao.ProviderMapper;
import com.linchen.dto.Provider;
import com.linchen.dto.ProviderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ProviderBo implements ProviderBoI {
    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public List<Provider> queryProviderList(String queryCode, String queryName) {
        return providerMapper.findProviderList(queryCode, queryName);
    }

    /*显示订单表供应商名称*/
    @Override
    public List<Provider> queryProviderName() {
        return providerMapper.selectByExample(new ProviderExample());
    }

    @Override
    public Integer addProvider(Provider provider) {
        provider.setCreatedBy(1L);
        provider.setCreationDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        return providerMapper.insert(provider);
    }

    @Override
    public String deleteProvider(Long id) {
        Integer i = providerMapper.deleteByPrimaryKey(id);
        if (i == null) {
            return "notexist";
        } else if (i > 0) {
            return "true";
        } else {
            return "false";
        }
    }

    @Override
    public Provider queryProviderById(Long id) {
        return providerMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateProviderById(Provider provider) {
        provider.setModifyDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        return providerMapper.updateProviderById(provider);
    }
}

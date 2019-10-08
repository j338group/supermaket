package cn.smbms.service;

import cn.smbms.dao.ProviderMapper;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.ProviderExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public List<Provider> queryProvideList1() {
        List<Provider> providers=providerMapper.queryProvideList1();
        return providers;
    }

    @Override
    public List<Provider> queryProvideList(String queryProCode, String queryProName) {
        Map<String, Object> param = new HashMap<>();
//        ProviderExample providerExample = new ProviderExample();
        param.put("queryProCode", queryProCode);
        param.put("queryProName", queryProName);
        return providerMapper.queryProvideList(param);
    }

    @Override
    public Provider findProById(String proId) {
        Provider provider = providerMapper.selectByPrimaryKey(Long.parseLong(proId));

        BeanUtils.copyProperties(provider, provider);

        return provider;
    }

    @Override
    public boolean delProById(String proId) {
        int i = providerMapper.deleteByPrimaryKey(Long.parseLong(proId));
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addPro(Provider provider, Long id) {
        provider.setCreatedBy(id);
        provider.setCreationDate(new Date());
        int i = providerMapper.insert(provider);
        if (i > 0)
            return true;
        return false;
    }

    @Override
    public boolean findUserByProCode(String ProCode) {
        ProviderExample example = new ProviderExample();
        ProviderExample.Criteria criteria = example.createCriteria();
        criteria.andProCodeEqualTo(ProCode);
        List<Provider> providers = providerMapper.selectByExample(example);
        if (providers.size() > 0)
            return true;
        return false;
    }
}

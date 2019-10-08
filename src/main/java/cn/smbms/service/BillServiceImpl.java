package cn.smbms.service;

import cn.smbms.dao.BillMapper;
import cn.smbms.dao.ProviderMapper;
import cn.smbms.pojo.*;
import cn.smbms.vo.BillVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillMapper billMapper;
    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public List<BillVo> queryBillList(String queryProductName, Integer queryProviderId, Integer queryIsPayment) {
        Map<String, Object> param = new HashMap<>();
        param.put("queryProductName", queryProductName);
        param.put("queryProviderId", queryProviderId);
        param.put("queryIsPayment", queryIsPayment);
        return billMapper.queryBillList(param);
    }

    @Override
    public BillVo findBillById(String billId) {
        Bill bill = billMapper.selectByPrimaryKey(Long.parseLong(billId));
        Provider provider = providerMapper.selectByPrimaryKey(bill.getProviderId());
        BillVo billVo = new BillVo();
        BeanUtils.copyProperties(bill, billVo);
        billVo.setProviderName(provider.getProName());
        return billVo;
    }

    @Override
    public boolean delBillById(String billId) {
        int i = billMapper.deleteByPrimaryKey(Long.parseLong(billId));
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addBill(Bill bill, Long id) {
        bill.setCreatedBy(id);
        bill.setCreationDate(new Date());
        int i = billMapper.insert(bill);
        if (i > 0)
            return true;
        return false;
    }

    @Override
    public boolean findBillByBillCode(String billCode) {
        BillExample example = new BillExample();
        BillExample.Criteria criteria = example.createCriteria();
        criteria.andBillCodeEqualTo(billCode);
        List<Bill> bills = billMapper.selectByExample(example);
        if (bills.size() > 0)
            return true;
        return false;
    }
}

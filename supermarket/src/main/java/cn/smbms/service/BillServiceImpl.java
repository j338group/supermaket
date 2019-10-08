package cn.smbms.service;

import cn.smbms.dao.BillMapper;
import cn.smbms.dao.ProviderMapper;
import cn.smbms.pojo.Bill;
import cn.smbms.pojo.BillExample;
import cn.smbms.pojo.Provider;
import cn.smbms.vo.BillVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<BillVo> queryBillList(String productName, Integer providerId,Integer isPayment) {
        Map<String,Object> param=new HashMap<>();
        param.put("productName",productName);
        param.put("providerId",providerId);
        param.put("isPayment",isPayment);

        return billMapper.selectBillList(param);
    }

    @Override
    public String deleteById(String id) {
         int i=billMapper.deleteByPrimaryKey(Long.parseLong(id));
            if (i>0){
                System.out.println("true.........................................................");
                return "true";

            }
        System.out.println("false..................................................................");
                return "false";

    }

    @Override
    public BillVo queryById(String id) {
        Bill bill = billMapper.selectByPrimaryKey(Long.parseLong(id));
        Provider provider=providerMapper.selectByPrimaryKey(Long.parseLong(id));
        BillVo billVo=new BillVo();
        BeanUtils.copyProperties(bill,billVo);
        billVo.setProviderName(provider.getProName());
        return billVo;
    }

    @Override
    public void updateBill(Bill bill) {
        BillExample billExample=new BillExample();
        BillExample.Criteria criteria = billExample.createCriteria();
        criteria.andBillCodeEqualTo(bill.getBillCode());
        billMapper.updateByExampleSelective(bill,billExample);
    }

    @Override
    public int addBill(Bill bill) {
        int i = billMapper.insert(bill);
        return i;
    }
}

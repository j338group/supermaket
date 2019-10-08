package site.benevolent.sm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.benevolent.sm.dao.BillsMapper;
import site.benevolent.sm.pojo.Bills;

import java.util.List;

@Service
public class BillBo {
    @Autowired
    private BillsMapper billsMapper;
    public List<Bills> queryByCondition(String productName,Long proId,Integer isPay){
        return billsMapper.findByCondition(productName,proId,isPay);
    }
    public Bills queryById(Long id){
        return billsMapper.findById(id);
    }
    public Boolean delById(Long id){
        return billsMapper.delById(id);
    }
    public int updateBill(Bills bill){
        return billsMapper.updateBill(bill);
    }
    public int addBill(Bills bill){
        return billsMapper.addBill(bill);
    }
}

package com.linchen.bo;

import com.linchen.dao.BillMapper;
import com.linchen.dto.Bill;
import com.linchen.dto.Provider;
import com.linchen.vo.BillVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class BillBo implements BillBoI {
    @Autowired
    private BillMapper billMapper;

    @Override
    public List<BillVo> queryBillList(String productName, Integer proId, Integer isPayment) {
        return billMapper.findBillList(productName, proId, isPayment);
    }

    @Override
    public int addBill(Bill bill) {
        bill.setCreatedBy(1L);
        bill.setCreationDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        return billMapper.insert(bill);
    }

    @Override
    public String deleteBill(Long id) {
        Integer i = billMapper.deleteByPrimaryKey(id);
        if (i == null) {
            return "notexist";
        } else if (i > 0) {
            return "true";
        } else {
            return "false";
        }
    }

    @Override
    public Bill queryBillById(Long id) {
        return billMapper.findBillById(id);
    }

    @Override
    public int updateBill(Bill bill) {
        bill.setModifyDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        return billMapper.updateBill(bill);
    }

}

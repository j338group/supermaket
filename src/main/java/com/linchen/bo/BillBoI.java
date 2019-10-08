package com.linchen.bo;

import com.linchen.dto.Bill;
import com.linchen.vo.BillVo;

import java.util.List;

public interface BillBoI {
    List<BillVo> queryBillList(String productName,Integer proId,Integer isPayment);

    int addBill(Bill bill);

    String deleteBill(Long id);

    Bill queryBillById(Long id);

    int updateBill(Bill bill);
}

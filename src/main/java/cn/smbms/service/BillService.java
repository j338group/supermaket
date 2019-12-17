package cn.smbms.service;

import cn.smbms.pojo.Bill;
import cn.smbms.vo.BillVo;

import java.util.List;

public interface BillService {

    List<BillVo> queryBillList(String productName, Integer providerId, Integer isPayment);

    String deleteById(String id);

    BillVo queryById(String id);

    void updateBill(Bill bill);

    int addBill(Bill bill);
}

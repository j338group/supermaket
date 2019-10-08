package cn.smbms.service;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.User;
import cn.smbms.vo.BillVo;
import cn.smbms.vo.UserVo;

import java.util.List;

public interface BillService {
    List<BillVo> queryBillList(String queryProductName, Integer queryProviderId, Integer queryIsPayment);

    //    List<BillVo> queryBillList();
    BillVo findBillById(String userId);

    boolean delBillById(String billId);

    boolean addBill(Bill bill, Long billId);

    boolean findBillByBillCode(String billCode);

}

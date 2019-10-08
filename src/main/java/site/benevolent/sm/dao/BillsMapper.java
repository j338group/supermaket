package site.benevolent.sm.dao;

import org.apache.ibatis.annotations.Param;
import site.benevolent.sm.pojo.Bills;

import java.util.List;

public interface BillsMapper {
    List<Bills> findByCondition(@Param("pName") String queryProductName,@Param("pId") Long queryProviderId,@Param("pay") Integer queryIsPayment);
    Bills findById(Long id);
    Boolean delById(Long id);
    int updateBill(Bills bill);
    int addBill(Bills bill);
}

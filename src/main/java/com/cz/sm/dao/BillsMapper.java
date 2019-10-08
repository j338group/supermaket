package com.cz.sm.dao;

import com.cz.sm.pojo.Bills;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillsMapper {
    List<Bills> findByCondition(@Param("pName") String queryProductName, @Param("pId") Long queryProviderId, @Param("pay") Integer queryIsPayment);
    Bills findById(Long id);
    Boolean delById(Long id);
    int updateBill(Bills bill);
    int addBill(Bills bill);
}

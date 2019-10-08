package com.linchen.dao;

import com.linchen.dto.Bill;
import com.linchen.dto.BillExample;
import java.util.List;

import com.linchen.vo.BillVo;
import org.apache.ibatis.annotations.Param;

public interface BillMapper {
    long countByExample(BillExample example);

    int deleteByExample(BillExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Bill record);

    int insertSelective(Bill record);

    List<Bill> selectByExample(BillExample example);

    Bill selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Bill record, @Param("example") BillExample example);

    int updateByExample(@Param("record") Bill record, @Param("example") BillExample example);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);

    List<BillVo> findBillList(@Param("productName") String productName,@Param("proId") Integer proId,@Param("isPayment") Integer isPayment);

    Bill findBillById(Long id);

    int updateBill(Bill bill);
}
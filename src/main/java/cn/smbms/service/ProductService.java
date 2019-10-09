package cn.smbms.service;

import cn.smbms.pojo.Bill;

import java.util.List;

public interface ProductService {

    List<Bill> queryBillList(String queryName,Integer roleId,int currentPageNo, int pageSize);
}

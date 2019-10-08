package com.linchen.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linchen.bo.BillBoI;
import com.linchen.bo.ProviderBoI;
import com.linchen.dto.Bill;
import com.linchen.dto.Provider;
import com.linchen.vo.BillVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jsp")
public class BillManagerAction {
    @Autowired
    private BillBoI billBoI;
    @Autowired
    private ProviderBoI providerBoI;

    /*获得订单列表*/
    @RequestMapping(value = "/bill.html", method = RequestMethod.GET)
    public String queryBillList(Model model, @RequestParam(value = "queryProductName", required = false) String productName,
                                @RequestParam(value = "queryProviderId", required = false) Integer proId,
                                @RequestParam(value = "queryIsPayment", required = false) Integer isPayment) {
        List<BillVo> billVoList = billBoI.queryBillList(productName, proId, isPayment);
        List<Provider> providerName = providerBoI.queryProviderName();

        model.addAttribute("billList", billVoList);
        model.addAttribute("providerList", providerName);

        model.addAttribute("queryProductName", productName);
        model.addAttribute("queryProviderId", proId);
        model.addAttribute("queryIsPayment", isPayment);

        return "billlist";
    }

    /*增加订单*/
    /*获取订单供应商列表*/
    @RequestMapping(value = "/prolist.html", method = RequestMethod.GET, produces = {"text/html;charset=UTF-8"})
    @ResponseBody
    public String proList() {
        List<Provider> providerList = providerBoI.queryProviderName();
        String prolist = null;
        try {
            prolist = new ObjectMapper().writeValueAsString(providerList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return prolist;
    }

    @RequestMapping(value = "/addbill.html", method = RequestMethod.GET)
    public String addBill() {
        return "billadd";
    }

    @RequestMapping(value = "/doaddbill.html", method = RequestMethod.POST)
    public String doAddBill(Bill bill) {
        billBoI.addBill(bill);
        return "redirect:/jsp/bill.html";
    }

    /*删除订单*/
    @RequestMapping("/deletebill.html")
    @ResponseBody
    public String deleteBill(@RequestParam("billid") Long id) {
        String str = billBoI.deleteBill(id);
        Map<String, String> res = new HashMap<>();
        res.put("delBillResult", str);

        String s = null;
        try {
            s = new ObjectMapper().writeValueAsString(res);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }

    /*修改订单*/
    @RequestMapping("/updatebill.html")
    public String updateBill(@RequestParam("billid") Long id,Model model){
        Bill bill = billBoI.queryBillById(id);
        model.addAttribute("bill",bill);
        return "billmodify";
    }

    @RequestMapping(value = "/doupdatebill.html",method = RequestMethod.POST)
    public String doUpdateBill(Bill bill){
        billBoI.updateBill(bill);
        return "redirect:/jsp/bill.html";
    }

    /*查询订单*/
    @RequestMapping("/querybill.html")
    public String queryBillById(@RequestParam("billid") Long id,Model model){
        Bill bill = billBoI.queryBillById(id);
        model.addAttribute("bill",bill);
        return "billview";
    }

}

package com.cz.sm.controller;

import com.cz.sm.pojo.Bills;
import com.cz.sm.pojo.Providers;
import com.cz.sm.service.BillBo;
import com.cz.sm.service.ProBo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jsp/bill")
public class BillController {
    @Autowired
    private BillBo billBo;
    @Autowired
    private ProBo proBo;

    @RequestMapping("/billlist.html")
    public String queryBillList(Model model,
                                @RequestParam(value = "queryProductName", required = false) String queryProductName,
                                @RequestParam(value = "queryProviderId", required = false) Long queryProviderId,
                                @RequestParam(value = "queryIsPayment", required = false) Integer queryIsPayment) {

        List<Bills> billsList = billBo.queryByCondition(queryProductName, queryProviderId, queryIsPayment);

        List<Providers> providersList = proBo.queryAllPro(null, null);
        model.addAttribute("providerList", providersList);
        model.addAttribute("billList", billsList);
        model.addAttribute("queryProductName", queryProductName);
        model.addAttribute("queryProviderId", queryProviderId);
        model.addAttribute("queryIsPayment", queryIsPayment);
        return "billlist";
    }

    @RequestMapping("/view/{billId}")
    public String queryBillInfo(@PathVariable("billId") Long billId, Model model) {
        Bills bills = billBo.queryById(billId);
        model.addAttribute("bill", bills);
        return "billview";
    }

    @RequestMapping("/delbill/{billId}")
    @ResponseBody
    public String delBill(@PathVariable("billId") Long id) {
        Boolean b = billBo.delById(id);
        Map<String, Object> result = new HashMap<>();
        result.put("delResult", b.toString());
        String resultJson = null;
        try {
            resultJson = new ObjectMapper().writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultJson;
    }

    @RequestMapping("/modify/{billId}")
    public String modifyBill(@PathVariable("billId") Long id, Model model) {
        Bills bills = billBo.queryById(id);
        model.addAttribute("bill", bills);
        return "billmodify";
    }

    @RequestMapping("/bill.update")
    public String billModify(Long id, String billCode, String productName, String productUnit, Double productCount, Double totalPrice, Integer providerId, Integer isPayment) {
        Bills bill = new Bills(id, billCode, productName, productUnit, productCount, totalPrice, isPayment, providerId);
        billBo.updateBill(bill);
        return "redirect:/jsp/bill/billlist.html";
    }


    @RequestMapping("/providerlist")
    @ResponseBody
    public List queryProviderList() {
        List<Providers> providersList = proBo.queryAllPro(null, null);
        return providersList;
    }

    @RequestMapping("/add.html")
    public String doAddUser() {
        return "billadd";
    }

    @RequestMapping("/bill.add")
    public String saveBill(String billCode, String productName, String productUnit, Double productCount, Double totalPrice, Integer providerId, Integer isPayment) {
        Bills bill = new Bills(billCode, productName, productUnit, productCount, totalPrice, isPayment, providerId);
        billBo.addBill(bill);
        System.out.println(providerId + "success!!!");
        return "redirect:/jsp/bill/billlist.html";
    }

}

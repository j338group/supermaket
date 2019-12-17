package cn.smbms.controller;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.service.BillService;
import cn.smbms.service.ProviderService;
import cn.smbms.vo.BillVo;
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
@RequestMapping("/jsp")
public class BillController {
    @Autowired
    private BillService billService;
    @Autowired
    private ProviderService providerService;
    //查询订单管理 billlist.jsp
    @RequestMapping("/bill.do")
    public String queryBillList(@RequestParam(value = "queryProductName",required =false)String productName,
                                @RequestParam(value = "queryProviderId",required = false) Integer providerId,
                                @RequestParam(value = "queryIsPayment",required = false) Integer isPayment,
                                Model model){
        List<BillVo> billList=billService.queryBillList(productName,providerId,isPayment);
        List<Provider> providerList=providerService.queryProvider1List();
        model.addAttribute("billList",billList);
        model.addAttribute("queryProductName",productName);
        model.addAttribute("queryProviderId",providerId);
        model.addAttribute("providerList",providerList);
        return "billlist";
    }
    //删除订单 billlist.js
    @RequestMapping("/billdel/{billid}")
    @ResponseBody
    public String delBill(@PathVariable("billid")String id) throws JsonProcessingException {
        String s=billService.deleteById(id);
        Map<String,String> result=new HashMap<>();
        result.put("delResult",s);
        String s1 = new ObjectMapper().writeValueAsString(result);
        return s1;
    }
    //查看订单billlist.js
    @RequestMapping("/billview/{billid}")
    public String queryBillInfo(@PathVariable("billid")String id,Model model){
        BillVo billVo=billService.queryById(id);
        model.addAttribute("bill",billVo);
        return "billview";
    }
    //修改订单billlist.js
    @RequestMapping("/billmodify/{billid}")
    public String modifyBill(@PathVariable("billid")String id,Model model){
        BillVo billVo=billService.queryById(id);
        model.addAttribute("bill",billVo);
        return "billmodify";
    }
    //修改订单中的下拉菜单
    @RequestMapping("/billlist")
    @ResponseBody
    public List<Provider> queryProviderList(){
        List<Provider> providerList = providerService.queryProvider1List();
        return providerList;
    }
    //进入添加页面
    @RequestMapping("/billadd.html")
    public String modifyBill(Bill bill){
        billService.updateBill(bill);
        return "billlist";
    }
    //添加订单
    @RequestMapping("addbill.html")
    public String addBill(){
        return "billadd";
    }
    @RequestMapping("/billadd.do")
    public String addBill(Bill bill){
        int i=billService.addBill(bill);
        return "billlist";
    }

}

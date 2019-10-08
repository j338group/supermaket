package cn.smbms.controller;


import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.service.BillService;
import cn.smbms.service.ProviderService;
import cn.smbms.vo.BillVo;
import cn.smbms.vo.UserVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jsp/bill")
public class BillController {
    @Autowired
    private BillService billService;
    @Autowired
    private ProviderService providerService;

    @RequestMapping("/billlist.html")
    public String queryBillList(Model model,
                                @RequestParam(value = "queryProductName", required = false) String queryProductName,
                                @RequestParam(value = "queryProviderId", required = false) Integer queryProviderId,
                                @RequestParam(value = "queryIsPayment",required = false)Integer queryIsPayment) {
        List<BillVo> billVoList = billService.queryBillList(queryProductName, queryProviderId,queryIsPayment);
        model.addAttribute("billVoList", billVoList);
        model.addAttribute("queryProductName", queryProductName);
        model.addAttribute("queryIsPayment",queryIsPayment);
        model.addAttribute("queryProviderId",queryProviderId);
        return "billlist";
    }

    @RequestMapping("billview/{billId}")
    public String queryBillInfo(@PathVariable("billId") String billId, Model model) {
        BillVo billVo = billService.findBillById(billId);
        model.addAttribute("bill", billVo);
        return "billview";
    }
    @RequestMapping("/delbill/{billId}")
    @ResponseBody
    public String delBill(@PathVariable("billId") String billId){
        Boolean b=billService.delBillById(billId);
        Map<String, Object> result = new HashMap<>();
        result.put("delResult",b.toString());
        String resultJson=null;
        try {
            resultJson = new ObjectMapper().writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultJson;
    }
    @RequestMapping("/billmodify/{billId}")
    public String modifyUser(@PathVariable("billId") String billId,Model model){
        BillVo billVo = billService.findBillById(billId);
        model.addAttribute("bill",billVo );
        return "billmodify";
    }
    @RequestMapping("/providelist")
    @ResponseBody
    public List queryRoleList(){
        List<Provider> providers = providerService.queryProvideList1();
        return providers;
    }
    @RequestMapping("/add.html")
    public String doAddBill(){
        return "billadd";
    }
    @RequestMapping("/addbill")
    public String addBill(Bill bill, HttpSession session){
        System.out.println("--"+bill);
       Bill loginuser = (Bill) session.getAttribute("billSession");
        Boolean b=billService.addBill(bill,loginuser.getId());
//TODO 跳转页面
        return "billlist";
    }
    @RequestMapping("/ucexist/{billCode}")
    @ResponseBody
    public Map<String,String> userCodeExist(@PathVariable("billCode") String billCode) {
        Boolean have = billService.findBillByBillCode(billCode);

        Map<String, String> map = new HashMap<>();
        if (have) {
            map.put("billCode", "exist");
        } else {
            map.put("billCode", "");
        }
        return map;
    }
}

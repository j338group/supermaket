package site.benevolent.sm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import site.benevolent.sm.pojo.Providers;
import site.benevolent.sm.service.ProBo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jsp/pro")
public class ProviderController {
    @Autowired
    private ProBo proBo;

    @RequestMapping("/provider.html")
    public String queryProList(Model model,
                               @RequestParam(value = "queryProCode", required = false) String queryProCode,
                               @RequestParam(value = "queryProName", required = false) String queryProName) {
        List<Providers> proList = proBo.queryAllPro(queryProCode, queryProName);
        model.addAttribute("providerList", proList);
        //回显
        model.addAttribute("queryProCode", queryProCode);
        //回显
        model.addAttribute("queryProName", queryProName);
        return "providerlist";
    }

    @RequestMapping("/providerview/{proId}")
    public String queryProInfo(@PathVariable("proId") String userId, Model model) {
        Providers provider = proBo.queryById(Long.parseLong(userId));
        model.addAttribute("provider", provider);
        return "providerview";
    }

    @RequestMapping("/delpro/{proid}")
    @ResponseBody
    public String delPro(@PathVariable("proid") String proId) {
        Boolean b = proBo.deleteById(Long.parseLong(proId));
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


    @RequestMapping("/providermodify/{proId}")
    public String modifyPro(@PathVariable("proId") String proId, Model model) {
        Providers providers = proBo.queryById(Long.parseLong(proId));
        model.addAttribute("provider", providers);
        return "providermodify";
    }

    @RequestMapping("/provider.update")
    public String ProModify(String proId, String proCode, String proName, String proContact, String proPhone, String proAddress, String proFax, String proDesc) {
        Providers pro = new Providers();
        pro.setId(Long.parseLong(proId));
        pro.setProCode(proCode);
        pro.setProName(proName);
        pro.setProContact(proContact);
        pro.setProPhone(proPhone);
        pro.setProAddress(proAddress);
        pro.setProFax(proFax);
        pro.setProDesc(proDesc);
        proBo.updateById(pro);
        return "redirect:/jsp/pro/provider.html";
    }
    @RequestMapping("/provideradd.html")
    public String addPro(){
        return "provideradd";
    }


    @RequestMapping("/provider.add")
    public String savePro(String proCode, String proName, String proContact, String proPhone, String proAddress, String proFax, String proDesc) {
        Providers pro = new Providers();
        pro.setProCode(proCode);
        pro.setProName(proName);
        pro.setProContact(proContact);
        pro.setProPhone(proPhone);
        pro.setProAddress(proAddress);
        pro.setProFax(proFax);
        pro.setProDesc(proDesc);
        proBo.savePro(pro);
        System.out.println("SUCCESS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "redirect:/jsp/pro/provider.html";
    }


}

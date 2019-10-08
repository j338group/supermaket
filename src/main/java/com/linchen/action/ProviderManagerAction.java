package com.linchen.action;

import com.linchen.bo.ProviderBoI;
import com.linchen.dto.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jsp")
public class ProviderManagerAction {
    @Autowired
    private ProviderBoI providerBoI;


    @RequestMapping(value = "/provider.html", method = RequestMethod.GET)
    public String providerList(Model model, @RequestParam(value = "queryProCode",required = false) String queryCode,@RequestParam(value = "queryProName",required = false) String queryName) {
        List<Provider> providerList = providerBoI.queryProviderList(queryCode,queryName);

        model.addAttribute("providerList", providerList);
        model.addAttribute("queryProCode",queryCode);
        model.addAttribute("queryProName",queryName);
        return "providerlist";
    }

    /*查看供应商*/
    @RequestMapping(value = "/queryprovider.html",method = RequestMethod.GET)
    public String queryProvider(@RequestParam("proid") Long id,Model model){
        Provider provider = providerBoI.queryProviderById(id);
        model.addAttribute("provider",provider);
        return "providerview";
    }

    //添加供应商
    @RequestMapping(value = "/provideradd.html",method = RequestMethod.GET)
    public String providerAdd(){
        return "provideradd";
    }

    @RequestMapping(value = "/doprovideradd.html",method = RequestMethod.POST)
    public String doProviderAdd(Provider provider){
        providerBoI.addProvider(provider);
        return "redirect:/jsp/provider.html";
    }

    /*删除供应商*/
    @RequestMapping(value = "/deleteprovider.html/{proid}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> deleteProvider(@PathVariable("proid") Long id){
        String s = providerBoI.deleteProvider(id);

        Map<String,String> map=new HashMap<>();
        map.put("delResult",s);
        return map;
    }

    /*修改供应商*/
    @RequestMapping(value = "/updateprovider.html",method = RequestMethod.GET)
    public String updateProvider(@RequestParam("proid") Long id,Model model){
        Provider provider = providerBoI.queryProviderById(id);
        model.addAttribute("provider",provider);
        return "providermodify";
    }

    @RequestMapping(value = "/doupdateprovider.html",method = RequestMethod.POST)
    public String doupdateProvider(Provider provider){
        providerBoI.updateProviderById(provider);
        return "redirect:/jsp/provider.html";
    }
}

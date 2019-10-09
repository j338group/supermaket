package cn.smbms.controller;

import cn.smbms.pojo.Provider;
import cn.smbms.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/jsp/provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;
    @RequestMapping("/providerlist.html")
    public String queryProviderList(Model model){
        List<Provider> providerlist = providerService.queryProviderList();

        model.addAttribute("providerList",providerlist);

        return "providerlist";
    }
}

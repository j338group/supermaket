package cn.smbms.controller;

import cn.smbms.pojo.Provider;
import cn.smbms.service.ProviderService;
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
@RequestMapping("/jsp/provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    @RequestMapping("/providerlist.html")
    public String queryProvideList(Model model,
                                   @RequestParam(value = "queryProCode", required = false) String queryProCode,
                                   @RequestParam(value = "queryProName", required = false) String queryProName) {

        List<Provider> providerList = providerService.queryProvideList(queryProCode, queryProName);
        model.addAttribute("providerList", providerList);
        model.addAttribute("queryProCode", queryProCode);
        model.addAttribute("queryProName", queryProName);
        return "providerlist";
    }

    @RequestMapping("providerview/{proId}")
    public String queryUserInfo(@PathVariable("proId") String proId, Model model) {
        Provider provider = providerService.findProById(proId);
        model.addAttribute("provider", provider);
        return "providerview";
    }

    @RequestMapping("/delprovider/{proId}")
    @ResponseBody
    public String delUser(@PathVariable("proId") String proId) {
        Boolean b = providerService.delProById(proId);
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
    public String modifyUser(@PathVariable("proId") String proId, Model model) {
        Provider provider = providerService.findProById(proId);
        model.addAttribute("provider", provider);
        return "providermodify";
    }

    @RequestMapping("/add.html")
    public String doAddUser() {
        return "provideradd";
    }

    @RequestMapping("/addprovider")
    public String addUser(Provider provider, HttpSession session) {
        System.out.println("--" + provider);
        Provider loginuser = (Provider) session.getAttribute("providerSession");
        Boolean b = providerService.addPro(provider, loginuser.getId());
//TODO 跳转页面
        return "providerlist";
    }

    @RequestMapping("/ucexist/{proCode}")
    @ResponseBody
    public Map<String, String> providerCodeExist(@PathVariable("proCode") String proCode) {
        Boolean have = providerService.findProByProCode(proCode);

        Map<String, String> map = new HashMap<>();
        if (have) {
            map.put("proCode", "exist");
        } else {
            map.put("proCode", "");
        }
        return map;
    }
}

package cn.smbms.controller;

import cn.smbms.service.Providerbo;
import cn.smbms.service.Rolebo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("jsp")
public class ProviderController {
    @Autowired
    private Providerbo providerbo  ;
    @Autowired
    private Rolebo rolebo;
}

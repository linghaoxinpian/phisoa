package com.shmilyou.web.controller;

import com.shmilyou.entity.Amateur;
import com.shmilyou.entity.Organization;
import com.shmilyou.entity.User;
import com.shmilyou.service.AmateurService;
import com.shmilyou.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/4
 */
@Controller
@RequestMapping(value = "/phisoa/register")
public class RegisterController extends BaseController {

    @Autowired
    private AmateurService amateurService;
    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value = "/organization")
    @ResponseBody
    public String registerOrganization(Organization organization) {
        return "{id:1,name:2}";
    }

    @RequestMapping(value = "/amateur")
    @ResponseBody
    public String registerAmateur(Amateur amateur) {
        return "{id:1,name:2}";
    }

    @RequestMapping(value = "/user")
    @ResponseBody
    public String registerUser(User user) {
        return "{id:1,name:2}";
    }
}

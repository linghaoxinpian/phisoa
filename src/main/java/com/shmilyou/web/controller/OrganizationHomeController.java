package com.shmilyou.web.controller;

/** Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/29 */

import com.shmilyou.entity.Organization;
import com.shmilyou.entity.OrganizationOverview;
import com.shmilyou.service.CourseOrderService;
import com.shmilyou.service.OrganizationService;
import com.shmilyou.web.resolver.LoginOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 机构的管理，非公开可访问的 */
@Controller
@RequestMapping("/phisoa/manager/organization")
public class OrganizationHomeController extends BaseController {

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private CourseOrderService courseOrderService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(LoginOrganization loginOrganization, ModelMap modelMap) {
        if (loginOrganization == null) {
            return "error";
        }
        //获取机构
        Organization organization = organizationService.queryById(loginOrganization.getId());
        //获取机构评分
        OrganizationOverview overview = organization.getOverview();
        //获取交易额
        int sumMoney = courseOrderService.getAllMoney(organization.getId());
        //成交量
        int count = courseOrderService.count();

        //
        modelMap.addAttribute("o", organization);
        modelMap.addAttribute("overview", overview);
        modelMap.addAttribute("sumMoney", sumMoney);
        modelMap.addAttribute("count", count);
        return "home_organization";
    }

    /** 基础信息管理 */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(LoginOrganization loginOrganization, ModelMap modelMap) {
        if (loginOrganization == null) {
            return "error";
        }
        //获取机构
        Organization organization = organizationService.queryById(loginOrganization.getId());


        //
        modelMap.addAttribute("o", organization);
        return "edit_organization";
    }
}

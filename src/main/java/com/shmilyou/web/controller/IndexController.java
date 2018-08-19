package com.shmilyou.web.controller;

import com.shmilyou.entity.Amateur;
import com.shmilyou.entity.Organization;
import com.shmilyou.entity.User;
import com.shmilyou.service.OrganizationService;
import com.shmilyou.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/16
 */
@Controller
@RequestMapping("/phisoa")
public class IndexController extends BaseController {
    @Autowired
    OrganizationService OrganizationService;


    @RequestMapping(value = {"/index", "/", ""})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login_in", method = RequestMethod.GET)
    public String loginIn() {
        return "login";
    }

    @RequestMapping(value = "/login_in_user", method = RequestMethod.POST)
    public String loginInUser(@ModelAttribute User user, HttpServletRequest request) {
        //todo:求学者登录校验
        if (user == null) {
            //return "false";
        }
        request.getSession().setAttribute(Constant.LOGIN_INFO, user);
        return "index";
    }

    @RequestMapping(value = "/login_in_organization", method = RequestMethod.POST)
    public String loginInOrganization(@ModelAttribute Organization organization, HttpServletRequest request) {
        //todo:机构登录校验
        request.getSession().setAttribute(Constant.LOGIN_INFO, organization);
        return "index";
    }

    @RequestMapping(value = "/login_in_amateur", method = RequestMethod.POST)
    public String loginInAmateur(@ModelAttribute Amateur amateur, HttpServletRequest request) {
        //todo:爱好者登录校验
        request.getSession().setAttribute(Constant.LOGIN_INFO, amateur);
        return "index";
    }

    @RequestMapping(value = "/login_out", method = RequestMethod.GET)
    public String loginOut(HttpServletRequest request) {
        request.getSession().removeAttribute(Constant.LOGIN_INFO);
        return "index";
    }

    /**
     * 分类查询
     *
     * @param searchStr 查询字符串
     * @param classify  0:机构，1：爱好者，2：标签
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Object search(String searchStr, String classify, Integer pageIndex, Integer pageSize) {
        pageIndex = 1;
        pageSize = 20;
        //todo：根据【0.机构名/1.爱好者名/2.标签】 搜索

        return null;
    }

}

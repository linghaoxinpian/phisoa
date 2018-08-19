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
import java.util.List;

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
    public String search(String searchStr, String classify, Integer pageIndex, Integer pageSize) {
        pageIndex = 1;
        pageSize = 20;
        //todo：根据【0.机构名/1.爱好者名/2.标签】 搜索

        return "searchResult";
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    @ResponseBody
    public List<Object> news() {
        //待定：行业资讯：展示文化动态机构前沿，多文化培训的重要性以及优秀机构展示
        return null;
    }

    @RequestMapping(value = "/aside", method = RequestMethod.POST)
    @ResponseBody
    public List<Object> aside() {
        //待定：机构栏目【参考腾讯网、】：该地区所有机构情况【若首推机构或个人饱和，此处依次往后展示】
        return null;
    }

    @RequestMapping(value = "/place_amateur_list", method = RequestMethod.POST)
    @ResponseBody
    public List<Object> amateurNews() {
        //待定：个人栏目：该地区所有个人情况
        return null;
    }

    @RequestMapping(value = " to_denounce", method = RequestMethod.GET)
    public String toDenounce() {
        return "to_denounce";
    }

    /**
     * 举报提交
     *
     * @param profile     举报人姓名
     * @param isAnonymity 是否匿名
     * @param content     举报内容
     */
    @RequestMapping(value = " to_denounce", method = RequestMethod.POST)
    public String toDenounce(String profile, String email, String phone, String isAnonymity, String content) {
        return "to_denounce";
    }
}

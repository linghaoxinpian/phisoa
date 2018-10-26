package com.shmilyou.web.controller;

import com.shmilyou.entity.Amateur;
import com.shmilyou.entity.Organization;
import com.shmilyou.entity.User;
import com.shmilyou.service.AmateurService;
import com.shmilyou.service.CategoryService;
import com.shmilyou.service.CourseService;
import com.shmilyou.service.OpenCourseService;
import com.shmilyou.service.OrganizationService;
import com.shmilyou.service.UserService;
import com.shmilyou.utils.Constant;
import com.shmilyou.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/26
 */
@Controller
@RequestMapping("/phisoa/login")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private AmateurService amateurService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private OpenCourseService openCourseService;


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String loginInUser(String account, String password, HttpServletRequest request, ModelMap modelMap) {
        //求学者登录校验
        if (!StringUtils.isEmpty(account) && !StringUtils.isEmpty(password)) {
            User user = userService.loginIn(account, password);
            if (user != null) {
                request.getSession().setAttribute(Constant.LOGIN_USER, user);
                return "index";
            }
        }
        modelMap.addAttribute("error", "用户名或密码错误");
        modelMap.addAttribute("account", account);
        return "login_user";
    }

    @RequestMapping(value = "/organization", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity loginInOrganization(String account, String password, HttpServletRequest request) {
        //机构登录校验
        if (!StringUtils.isEmpty(account) && !StringUtils.isEmpty(password)) {
            Organization organization = organizationService.loginIn(account, password);
            if (organization != null) {
                request.getSession().setAttribute(Constant.LOGIN_ORGANIZATION, organization);
                return WebUtils.ok();
            }
        }
        return WebUtils.error("登录失败");
    }

    @RequestMapping(value = "/amateur", method = RequestMethod.POST)
    public String loginInAmateur(String account, String password, HttpServletRequest request) {
        //爱好者登录校验
        if (StringUtils.isEmpty(account) && StringUtils.isEmpty(password)) {
            Amateur amateur = amateurService.loginIn(account, password);
            if (amateur != null) {
                request.getSession().setAttribute(Constant.LOGIN_AMATEUR, amateur);
                return "index";
            }
        }
        return "login";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String loginInUser() {
        return "login_user";
    }

    @RequestMapping(value = "/organization", method = RequestMethod.GET)
    public String loginInOrganization() {
        return "login_organization";
    }

    @RequestMapping(value = "/login_out", method = RequestMethod.GET)
    public String loginOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "index";
    }
}

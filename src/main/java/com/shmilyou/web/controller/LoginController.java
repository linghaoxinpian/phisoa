package com.shmilyou.web.controller;

import com.alibaba.fastjson.JSON;
import com.shmilyou.entity.Amateur;
import com.shmilyou.entity.Organization;
import com.shmilyou.entity.User;
import com.shmilyou.service.AmateurService;
import com.shmilyou.service.CategoryService;
import com.shmilyou.service.CourseService;
import com.shmilyou.service.OpenCourseService;
import com.shmilyou.service.OrganizationService;
import com.shmilyou.service.UserService;
import com.shmilyou.service.bo.AppIdAndOpenIdAndAccessToken;
import com.shmilyou.utils.Constant;
import com.shmilyou.utils.WebUtils;
import com.shmilyou.web.resolver.LoginOrganization;
import com.shmilyou.web.resolver.LoginUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

    @RequestMapping(value = "")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String loginInUser(String account, String password, HttpServletRequest request, ModelMap modelMap, HttpServletResponse response) {
        //求学者登录校验
        if (!StringUtils.isEmpty(account) && !StringUtils.isEmpty(password)) {
            User user = userService.loginIn(account, password);
            if (user != null) {
                LoginUser loginUser = new LoginUser();
                BeanUtils.copyProperties(user, loginUser);
                request.getSession().setAttribute(Constant.LOGIN_USER, loginUser);
                //跳转之前访问的页面
                String requestURI = (String) request.getSession().getAttribute("requestURI");
                if (requestURI != null) {
                    try {
                        response.sendRedirect(requestURI);
                    } catch (IOException e) {
                        logger.error(e.getLocalizedMessage(), e);
                    }
                }
                return "redirect:/phisoa";
            }
        }
        modelMap.addAttribute("error", "用户名或密码错误");
        modelMap.addAttribute("account", account);
        return "login_user";
    }

    @RequestMapping(value = "/organization", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity loginInOrganization(String account, String password, HttpServletRequest request, HttpServletResponse response) {
        //机构登录校验
        if (!StringUtils.isEmpty(account) && !StringUtils.isEmpty(password)) {
            Organization organization = organizationService.loginIn(account, password);
            if (organization != null) {
                LoginOrganization loginOrganization = new LoginOrganization();
                BeanUtils.copyProperties(organization, loginOrganization);
                request.getSession().setAttribute(Constant.LOGIN_ORGANIZATION, loginOrganization);
                //跳转之前访问的页面
                String requestURI = (String) request.getSession().getAttribute("requestURI");
                if (requestURI != null) {
                    try {
                        response.sendRedirect(requestURI);
                    } catch (IOException e) {
                        logger.error(e.getLocalizedMessage(), e);
                    }
                }
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
        return "redirect:/phisoa";
    }

    /** 求学者微信登录 */
    @RequestMapping(value = "/weixin")
    public String weixinLogin(String code) {
        logger.info("code:" + code);
        return "index";
    }

    /** 求学者QQ登录 */
    @RequestMapping(value = "/QQ")
    public String QQLogin(String access_token, HttpSession session) {
        logger.info("测试：access_token=" + access_token);
        //1.未获取到token
        if (StringUtils.isEmpty(access_token)) {
            return "qq_auth";
        }
        //2.已获取到token
        String json = WebUtils.getJsonFromUrl("https://graph.qq.com/oauth2.0/me?access_token=" + access_token);
        logger.info("测试:原始json=" + json);
        json = json.substring(json.indexOf("{"), json.indexOf("}") + 1);
        logger.info("测试:截取后的json=" + json);
        AppIdAndOpenIdAndAccessToken qqLoginHelper = JSON.parseObject(json, AppIdAndOpenIdAndAccessToken.class);
        qqLoginHelper.setAccess_token(access_token);
        User user = userService.QQLogin(qqLoginHelper);

        if (user == null) {
            //网络错误之类的情况导致未能获取QQ基本信息
            return "error";
        }
        //3.登录信息存入session
        LoginUser loginUser = new LoginUser();
        loginUser.setId(user.getId());
        loginUser.setNickname(user.getNickname());
        session.setAttribute(Constant.LOGIN_USER, loginUser);


        return "redirect:/phisoa";
    }

}

package com.shmilyou.web.interceptor;

import com.shmilyou.utils.ConfigUtils;
import com.shmilyou.utils.Constant;
import com.shmilyou.web.resolver.LoginOrganization;
import com.shmilyou.web.resolver.LoginUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/17
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        //System.out.println("-------1.在请求的方法之前执行,如果返回true,则继续向后执行--------");

        if (ConfigUtils.IS_DEBUG) {
            //测试环境
            LoginUser loginUser = LoginUser.getDefault();
            LoginOrganization loginOrganization = LoginOrganization.getDefault();
            request.getSession().setAttribute(Constant.LOGIN_USER, loginUser);
            request.getSession().setAttribute(Constant.LOGIN_ORGANIZATION, loginOrganization);
            return true;
        }

        // 是否登录判断
        if (request.getSession().getAttribute(Constant.LOGIN_USER) == null &&
                request.getSession().getAttribute(Constant.LOGIN_ORGANIZATION) == null) {
            response.sendRedirect(request.getContextPath() + "/phisoa/login");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {
        //System.out.println("----2.在请求的方法执行之后执行(没有抛异常的话)----------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //System.out.println("----3.在请求的方法执行之后,无论是否抛出异常,通常用来进行异常处理----------");
    }
}

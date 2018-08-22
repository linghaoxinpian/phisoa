package com.shmilyou.web.interceptor;

import com.shmilyou.entity.User;
import com.shmilyou.utils.Constant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/17
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //System.out.println("-------1.在请求的方法之前执行,如果返回true,则继续向后执行--------");

        //测试环境
        User user = new User();
        user.setId("2c2dae92-a05c-11e8-be4c-c60adc336b7d");
        user.setName("admin");

        // 是否登录判断
        httpServletRequest.getSession().setAttribute(Constant.LOGIN_INFO, user);
        if (httpServletRequest.getSession().getAttribute(Constant.LOGIN_INFO) == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
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

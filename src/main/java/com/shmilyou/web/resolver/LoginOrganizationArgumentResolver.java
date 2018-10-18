package com.shmilyou.web.resolver;

import com.shmilyou.utils.Constant;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/18
 */

/**
 * 将登录机构的信息注入到请求方法的参数中，
 * <p>such as: xxx(Organization organization)</p>
 */
public class LoginOrganizationArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //判断是否支持
        //return parameter.hasParameterAnnotation(OrganizationLogin.class)  //1.通过注解方式
        return parameter.getParameterType() == LoginOrganization.class;  //2.直接判断 类型方式
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory webDataBinderFactory) {
        Object loginOrganization = webRequest.getNativeRequest(HttpServletRequest.class).
                getSession().getAttribute(Constant.LOGIN_INFO);
        return loginOrganization;
    }
}

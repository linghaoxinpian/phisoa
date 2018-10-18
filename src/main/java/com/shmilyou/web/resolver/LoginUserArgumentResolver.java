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
 * 将登录用户的信息注入到请求方法的参数中，
 * <p>such as: xxx(User user)</p>
 */
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //判断是否支持
        //return parameter.hasParameterAnnotation(UserLogin.class)//通过注解方式
        return parameter.getParameterType() == LoginUser.class;//直接判断 类型方式
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory webDataBinderFactory) {
        /**
         * 到了这里说明 ：方法参数中有匹配的情况那么我们可以 获得用户了
         */

        Object loginUser = webRequest.getNativeRequest(HttpServletRequest.class).
                getSession().getAttribute(Constant.LOGIN_USER);

        /**
         * if(user==null){
         *    throw new UserNotFoundException();
         *   }
         */
        /**
         * Tips: 在自定义参数注入中，有时也需要在路径中取出参数的情况，就像使用@PathVariable一样，
         *  我们可以使用以下这行代码来取，Spring把路径中的参数和值封装到了一个Map里面
         *  ，并放进了Request中
         *  Map<String, String> uriTemplateVars =
         *      (Map<String, String>) webRequest.getAttribute(
         *      HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
         */
        return loginUser;
    }
}

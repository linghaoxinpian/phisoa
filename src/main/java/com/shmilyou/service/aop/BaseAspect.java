package com.shmilyou.service.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/23
 */
@Aspect
//@Component    注释掉不启用aop
public class BaseAspect {

    @Before(value = "execution(* loginIn(..))")
    public void beforeLoginIn() {
        System.out.println("登录之前");
    }
}

package com.shmilyou.web.resolver;

import com.shmilyou.entity.CourseOrder;
import lombok.Data;

import java.util.Date;
import java.util.Stack;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/18
 */

/**
 * 用户登录信息
 */
@Data
public class LoginUser {

    private String id;

    private String name;

    private String nickname;

    private String password;

    private String headImg;

    private String phone;

    private String email;

    /** 地区编码 */
    private String areaCode;

    /**
     * 出生日期
     */
    private Date birthDay;

    private Integer gender;

    //----------------- 非DB字段 -----------------

//    /**
//     * 擅长的文化
//     */
//    private List<UserTag> strongList;
//
//    /**
//     * 爱好的文化,用于论坛
//     */
//    private List<UserTag> interestList;

    /** 本次登录产生的订单 */
    private Stack<CourseOrder> orders;

    /** 测试环境 */
    public static LoginUser getDefault() {
        LoginUser loginUser = new LoginUser();
        loginUser.setId("efd6cc26-d726-11e8-a081-36261d1e43f2");
        loginUser.setName("admin");
        return loginUser;
    }
}

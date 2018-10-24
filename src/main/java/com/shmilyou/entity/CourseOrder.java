package com.shmilyou.entity;

import lombok.Data;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/24
 */
// 用户购买课程
@Data
public class CourseOrder extends BaseEntity {

    private String id;

    /**
     * 订单号
     */
    private String orderNum;

    private String courseId;

    private String userId;

    //----------------- 非DB字段 -----------------

    private Course course;

    private User user;
}

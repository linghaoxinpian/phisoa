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

    /** 订单金额 */
    private int cash;

    /** 订单状态 */
    private int status;

    /** 该笔订单已评论的条数 */
    private int commentsNum;

    private String courseId;

    private String userId;

    private String organizationId;

    //----------------- 非DB字段 -----------------

    //课程状态
    public final static int FAIL = 0;
    public final static int SUCCESS = 1;
    public final static int ERROR = 2;

    private Course course;

    private User user;

    private Organization organization;

}

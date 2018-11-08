package com.shmilyou.entity;

import lombok.Data;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/18
 */

/**
 * 用户对机构的评论表
 */
@Data
public class OrganizationComment extends BaseEntity {

    private String id;

    private String comment;

    /** 评论时所带的图片 */
    private String pictures;

    /**
     * 用户对机构的总体打星
     */
    private Integer star;

    /**
     * 六边形打分--begin
     */
    //信誉度
    private Integer creditScore;
    //环境
    private Integer environmentScore;
    //师资
    private Integer facultyScore;
    //效果
    private Integer effectScore;
    //认证等级->见level字段
    //满意度
    private Integer satisfactionScore;
    //六边形打分--end

    private String organizationId;

    private String userId;  //commenter

    //----------------- 非DB字段 -----------------

    private User commenter;

    private Organization organization;

    //----------------- 附加属性 -----------------

    /** 存放解析后的(pictures属性)图片名称 */
    private List<String> parsedPictures;
}

package com.shmilyou.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created with 530060499@qq.com
 * Date: 2018/8/16
 */

/**
 * 机构的课程
 */
@Data
public class Course extends BaseEntity {

    private String id;

    private String name;

    private String description;

    private String picUrl;

    private int price;

    private int originalPrice;

    /** 课程等级：初级 */
    private String level;

    /** 适合的等级：入门级，中级 */
    private String suitable;

    /** 教学形式：班级上课 */
    private String trainingModel;

    /** 每次上课时长 */
    private String duration;

    /** 教学环境 */
    private String environment;

    /** 特色 */
    private String feature;

    /** 课程目标 */
    private String target;

    /** 开课时间 */
    private Date startTime;

    /** 课程相关的拍摄照片 */
    private String pictures;

    private String ownerId;

    /** 课程所属分类Id */
    private String categoryId;

    /** 课程讲师Id */
    private String lecturerId;

    private Date addTime;

    //----------------- 非DB字段 -----------------

    /** 标签名 */
    private String tagName;

    /** 所属机构 */
    private Organization organization;

    /** 课程讲师 */
    private Lecturer lecturer;

    //----------------- 附加属性 -----------------

    /** 解析后的照片名称 */
    private List<String> parsedPictures;

}

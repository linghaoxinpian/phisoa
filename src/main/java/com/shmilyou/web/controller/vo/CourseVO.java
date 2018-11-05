package com.shmilyou.web.controller.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;

@Data
public class CourseVO {

    private String id;

    private String name;

    private String description;

    /** 课程封面 */
    private MultipartFile picUrl;

    private BigDecimal price;

    private BigDecimal originalPrice;

    /** 课程等级：初级 */
    private String level;

    /** 适合的等级：入门级，中级 */
    private String suitable;

    /** 教学形式：班级上课 */
    private String trainingMode;

    /** 每次上课时长 */
    private String duration;

    /** 教学环境 */
    private String environment;

    /** 特色 */
    private String feature;

    /** 课程目标 */
    private String target;

    /** 开课时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /** 课程相关的拍摄照片 */
    private LinkedList<MultipartFile> pictures;

    private String ownerId;

    /** 课程所属分类Id */
    private String categoryId;

    /** 课程讲师Id */
    private String lecturerId;

}

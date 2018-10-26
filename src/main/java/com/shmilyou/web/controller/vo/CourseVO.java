package com.shmilyou.web.controller.vo;

import lombok.Data;

@Data
public class CourseVO {

    private String name;

    private String description;

    private String picUrl;

    private Integer price;

    private int originalPrice;

    private String categoryId;

}

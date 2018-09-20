package com.shmilyou.entity;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/20
 */

import lombok.Data;

/**
 * 地区
 */
@Data
public class Area extends BaseEntity {

    private int areaId;

    /**
     * 地区编码
     */
    private String areaCode;

    /**
     * 地区名
     */
    private String areaName;

    /**
     * 地区级别（1:省份province,2:市city,3:区县district,4:街道street）
     */
    private Integer level;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 城市中心点（即：经纬度坐标）
     */
    private String center;

    /**
     * 地区父节点
     */
    private Integer parentId;

    //非DB字段
    //省份
    private static final int PROVINCE = 1;
    //市
    private static final int CITY = 2;
    //区县
    private static final int DISTRICT = 3;
    //街道
    private static final int STREET = 4;
}

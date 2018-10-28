package com.shmilyou.entity;

import lombok.Data;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/21
 */
@Data
public class OrganizationOverview extends BaseEntity {

    private String id;

    /**
     * 求学者打星总数量记录
     */
    private int star1;
    private int star2;
    private int star3;
    private int star4;
    private int star5;

    // 计算后的总体能力图数值
    private int creditUltimate;
    private int environmentUltimate;
    private int facultyUltimate;
    private int effectUltimate;
    private int satisfactionUltimate;
    private int certificationLevel; //认证等级，organization_level表中的level值

    private String organization_id;
}

package com.shmilyou.entity;

import lombok.Data;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/19
 */

/**
 * 机构的教师信息
 */
@Data
public class OrganizationTeacher extends BaseEntity {

    private String id;

    private String name;

    /**
     * 简历
     */
    private String resume;

    /**
     * 教龄，资历
     */
    private Integer seniority;

    /**
     * 技能，如：舞蹈|钢琴
     */
    private String skill;

    private String organizationId;
}

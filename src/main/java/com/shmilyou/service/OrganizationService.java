package com.shmilyou.service;

import com.shmilyou.entity.Organization;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
public interface OrganizationService extends BaseService<Organization> {

    /**
     * 根据机构名模糊查询机构
     *
     * @param organizationName 机构名
     * @return
     */
    List<Organization> searchByName(String organizationName);

    /**
     * 根据地区查询机构
     *
     * @param place
     * @return
     */
    List<Organization> searchByPlace(String place);

    /**
     * 根据【单个标签】查询机构
     *
     * @param tagName
     * @param pageIndex 当前页页码
     * @return
     */
    List<Organization> searchByTag(String tagName, String pageIndex, String pageSize);

    /**
     * 根据手机号、邮箱登录
     *
     * @param account  手机号、邮箱登录
     * @param password 密码，需加盐处理
     * @return
     */
    Organization loginIn(String account, String password);

    /**
     * 注册
     */
    int register(Organization organization);
}

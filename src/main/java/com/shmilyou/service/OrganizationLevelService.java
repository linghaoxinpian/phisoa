package com.shmilyou.service;

import com.shmilyou.entity.OrganizationLevel;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
public interface OrganizationLevelService extends BaseService<OrganizationLevel> {

    List<OrganizationLevel> queryByIds(List<String> levelIds);
}

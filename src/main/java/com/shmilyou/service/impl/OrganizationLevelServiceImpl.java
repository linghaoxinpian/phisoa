package com.shmilyou.service.impl;

import com.shmilyou.entity.OrganizationLevel;
import com.shmilyou.repository.OrganizationLevelRepository;
import com.shmilyou.service.OrganizationLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
@Service
public class OrganizationLevelServiceImpl extends BaseServiceImpl<OrganizationLevel> implements OrganizationLevelService {

    @Autowired
    OrganizationLevelServiceImpl(OrganizationLevelRepository baseRepository) {
        super(baseRepository);
    }

    @Autowired
    private OrganizationLevelRepository organizationLevelRepository;

    @Override
    public List<OrganizationLevel> queryByIds(List<String> levelIds) {
        return organizationLevelRepository.queryByIds(levelIds);
    }
}

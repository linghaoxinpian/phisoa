package com.shmilyou.service.impl;

import com.shmilyou.entity.Organization;
import com.shmilyou.repository.OrganizationRepository;
import com.shmilyou.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
public class OrganizationServiceImpl extends BaseServiceImpl<Organization> implements OrganizationService {

    @Autowired
    OrganizationServiceImpl(OrganizationRepository baseRepository) {
        super(baseRepository);
    }
}

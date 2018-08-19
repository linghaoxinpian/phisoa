package com.shmilyou.service.impl;

import com.shmilyou.entity.Organization;
import com.shmilyou.repository.OrganizationRepository;
import com.shmilyou.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
@Service
public class OrganizationServiceImpl extends BaseServiceImpl<Organization> implements OrganizationService {

    @Autowired
    OrganizationServiceImpl(OrganizationRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Organization> searchByName(String organizationName) {
        return null;
    }

    @Override
    public List<Organization> searchByPlace(String place) {
        return null;
    }

    @Override
    public List<Organization> searchByTag(String tagName, String pageIndex, String pageSize) {
        return null;
    }
}

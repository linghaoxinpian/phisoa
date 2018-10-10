package com.shmilyou.service.impl;

import com.shmilyou.entity.Organization;
import com.shmilyou.entity.OrganizationTag;
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
    @Autowired
    private OrganizationRepository organizationRepository;

    //-------------------方法区-------------------
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

    @Override
    public Organization loginIn(String account, String password) {
        return null;
    }

    @Override
    public int register(Organization organization) {
        return organizationRepository.register(organization);
    }

    @Override
    public List<Organization> queryByTagId(String tagId, int pageIndex, int pageSize) {
        return organizationRepository.queryByTagId(tagId,pageIndex,pageSize);
    }

    @Override
    public List<Organization> indexRecommendTop() {
        return null;
    }

    @Override
    public int addOrganizationTag(List<OrganizationTag> organizationTags) {
        organizationRepository.insertOrganizationTag(organizationTags);
        return 0;
    }

}

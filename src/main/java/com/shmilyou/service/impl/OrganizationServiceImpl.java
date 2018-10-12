package com.shmilyou.service.impl;

import com.shmilyou.entity.Organization;
import com.shmilyou.entity.OrganizationTag;
import com.shmilyou.repository.OrganizationRepository;
import com.shmilyou.service.OrganizationService;
import com.shmilyou.utils.Constant;
import com.shmilyou.utils.Encrypt;
import com.shmilyou.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String type;
        if (Utils.isEmail(account)) {
            //邮箱登录
            type = "email";
        } else {
            //手机号登录
            type = "phone";
        }

        //帐号查询
        Map<String, String> map = new HashMap<>();
        map.put(type, account);
        map.put("password", Encrypt.string2SHA256(password + Constant.SALT));
        List<Organization> organizations = queryByColumns(map);
        if (organizations.size() > 0) {
            return organizations.get(0);
        }
        return null;
    }

    @Override
    public int register(Organization organization) {
        return organizationRepository.register(organization);
    }

    @Override
    public List<Organization> queryByTagId(String tagId, int pageIndex, int pageSize) {
        return organizationRepository.queryByTagId(tagId, pageIndex, pageSize);
    }

    @Override
    public List<Organization> indexRecommendTop() {
        return null;
    }

    @Override
    public int addOrganizationTag(List<OrganizationTag> organizationTags) {
        return organizationRepository.insertOrganizationTag(organizationTags);

    }

}

package com.shmilyou.service.impl;

import com.shmilyou.entity.OrganizationComment;
import com.shmilyou.repository.OrganizationCommentRepository;
import com.shmilyou.service.OrganizationCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/20
 */
@Service
public class OrganizationCommentServiceImpl extends BaseServiceImpl<OrganizationComment> implements OrganizationCommentService {

    @Autowired
    OrganizationCommentServiceImpl(OrganizationCommentRepository baseRepository) {
        super(baseRepository);
    }
}

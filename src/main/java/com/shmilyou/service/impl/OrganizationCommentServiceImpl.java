package com.shmilyou.service.impl;

import com.shmilyou.entity.OrganizationComment;
import com.shmilyou.repository.OrganizationCommentRepository;
import com.shmilyou.service.OrganizationCommentService;
import com.shmilyou.service.bo.OrganizationCommentBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/20
 */
@Service
public class OrganizationCommentServiceImpl extends BaseServiceImpl<OrganizationComment> implements OrganizationCommentService {

    @Autowired
    OrganizationCommentServiceImpl(OrganizationCommentRepository baseRepository) {
        super(baseRepository);
    }

    //-------------------方法区-------------------

    @Override
    public OrganizationCommentBO loadCommentsAndOverallGraph(String organizationId, int pageIndex, int pageSize) {
        List<OrganizationComment> comments = queryByColumn("organizationId", organizationId, pageIndex, pageSize);
        //计算 六边形得分
        OrganizationCommentBO commentBO = loadOverallGraph(organizationId);
        commentBO.setComments(comments);
        return commentBO;
    }

    public OrganizationCommentBO loadOverallGraph(String organizationId) {
        OrganizationCommentBO commentBO = new OrganizationCommentBO();
        //todo:新建overall表
        return commentBO;
    }
}

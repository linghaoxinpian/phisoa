package com.shmilyou.repository;

import com.shmilyou.BaseTest;
import com.shmilyou.entity.OrganizationComment;
import com.shmilyou.service.job.OrganizationOverViewJob;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/10
 */
public class OrganizationCommentRepositoryTest extends BaseTest {
    @Autowired
    private OrganizationCommentRepository organizationCommentRepository;
    @Autowired
    private OrganizationOverViewJob organizationOverViewJob;

    @Test
    public void test1() {
        List<OrganizationComment> list = organizationCommentRepository.querySumScores();
    }

    @Test
    public void test2() {
        organizationOverViewJob.updateAllScore();
    }
}

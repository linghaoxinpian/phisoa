package com.shmilyou.repository;

import com.shmilyou.BaseTest;
import com.shmilyou.entity.OrganizationTag;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/10
 */
public class OrganizationRepositoryTest extends BaseTest {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Test
    public void insertOrganizationTagTest() {
        List<OrganizationTag> list = new ArrayList<>();
        list.add(new OrganizationTag(null, "1", "1"));
        list.add(new OrganizationTag(null, "1", "2"));
        list.add(new OrganizationTag(null, "1", "3"));
        int i = organizationRepository.insertOrganizationTag(list);
        System.out.println("批量插入" + list.size() + "条数据，实际插入" + i + "条数据");
    }
}

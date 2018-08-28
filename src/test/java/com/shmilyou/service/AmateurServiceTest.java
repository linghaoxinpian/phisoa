package com.shmilyou.service;

import com.shmilyou.BaseTest;
import com.shmilyou.entity.Amateur;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/23
 */
public class AmateurServiceTest extends BaseTest {

    @Autowired
    private AmateurService amateurService;

    @Test
    public void searchByPlace() {
        List<Amateur> amateur_list = amateurService.searchByPlace("六安");
    }

    @Test
    public void searchByTag() {
        List<Amateur> amateurs = amateurService.searchByTag("周杰伦", 0, 2);
    }
}

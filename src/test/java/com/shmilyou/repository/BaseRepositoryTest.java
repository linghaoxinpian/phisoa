package com.shmilyou.repository;

import com.shmilyou.BaseTest;
import com.shmilyou.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/15
 */

public class BaseRepositoryTest extends BaseTest {
    @Autowired
    private BaseRepository<User> baseRepository;

    @Test
    public void delete() {
        int affectRow = baseRepository.delete("user", "2c2dae92-a05c-11e8-be4c-c60adc336b7d");
        System.out.println(affectRow);
    }
}

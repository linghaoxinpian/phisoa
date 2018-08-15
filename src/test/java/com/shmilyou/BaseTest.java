package com.shmilyou;

import com.shmilyou.entity.User;
import com.shmilyou.repository.BaseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring-mvc.xml",
        "classpath:spring-mybatis.xml"
})
public class BaseTest {
    @Autowired
    private BaseRepository<User> baseRepository;

    @Test
    public void queryById() {
        User user = baseRepository.queryById("user", "2c2dae92-a05c-11e8-be4c-c60adc336b7d");
        System.out.println("===" + user);
    }

    @Test
    public void delete() {
        int affectRow = baseRepository.delete("user", "2c2dae92-a05c-11e8-be4c-c60adc336b7d");
        System.out.println(affectRow);
    }

    @Test
    public void queryByColumn() {
        List<User> users = baseRepository.queryByColumn("user", "name", "admin");
        User user = new User();
        BeanUtils.copyProperties(users.get(0), user);
        System.out.println("====" + users.get(0));
    }
}

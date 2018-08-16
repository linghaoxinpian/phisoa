package com.shmilyou.service;

import com.shmilyou.BaseTest;
import com.shmilyou.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/15
 */
public class UserServiceTest extends BaseTest {

    @Autowired
    UserService userService;

    @Test
    public void insert() {
        User user = new User();
        user.setName("Exadmin");
        user.setNickName("Exadmin");
        int offsetRow = userService.insert(user);
        System.out.println("===========" + offsetRow + "-==========" + user.getId());
    }

    @Test
    public void update() {
        User user = new User();
        user.setId("22");
        user.setName("Exadmin");
        user.setNickName("Ex");
        int offsetRow = userService.insert(user);
        int offsetRow1 = userService.update(user);
        System.out.println("=====" + offsetRow1);
    }

    @Test
    public void queryById() {
        User user = new User();
        user.setName("Exadmin");
        user.setNickName("Exadmin");
        int offsetRow = userService.insert(user);
        User user1 = userService.queryById(user.getId());
    }

    @Test
    public void queryByColumn() {
        List<User> users = userService.queryByColumn("nickName", "admin");
    }

    @Test
    public void queryByColumns() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "admin");
        map.put("nickName", "admin");
        List<User> users = userService.queryByColumns(map);

    }

}

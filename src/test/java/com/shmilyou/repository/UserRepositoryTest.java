package com.shmilyou.repository;

import com.shmilyou.BaseTest;
import com.shmilyou.entity.UserTag;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/10
 */
public class UserRepositoryTest extends BaseTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void insertAmateurTagTest() {
        List<UserTag> list = new ArrayList<>();
        list.add(new UserTag(null, "1", "1", UserTag.STRONG_TAG));
        list.add(new UserTag(null, "1", "2", UserTag.STRONG_TAG));
        list.add(new UserTag(null, "1", "3", UserTag.STRONG_TAG));
        int i = userRepository.insertUserTag(list);
        System.out.println("批量插入" + list.size() + "条数据，实际插入" + i + "条数据");
    }
}

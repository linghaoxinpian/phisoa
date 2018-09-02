package com.shmilyou.service.impl;

import com.shmilyou.entity.User;
import com.shmilyou.entity.UserTag;
import com.shmilyou.repository.UserRepository;
import com.shmilyou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/15
 */

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    UserServiceImpl(UserRepository baseRepository) {
        super(baseRepository);
    }

    //-------------------方法区-------------------
    @Override
    public int register(User user) {
        return insert(user);
    }

    @Override
    public User loginIn(String account, String password) {
        //todo:
        return null;
    }

    @Override
    public List<UserTag> getUserTagByUserId(String userId, String tagClassify) {
        //todo:
        return null;
    }
}

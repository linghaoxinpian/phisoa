package com.shmilyou.service.impl;

import com.shmilyou.entity.User;
import com.shmilyou.entity.UserTag;
import com.shmilyou.repository.UserRepository;
import com.shmilyou.service.UserService;
import com.shmilyou.utils.Constant;
import com.shmilyou.utils.Encrypt;
import com.shmilyou.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private UserRepository userRepository;

    //-------------------方法区-------------------
    @Override
    public int register(User user) {
        String password = Encrypt.string2SHA256(user.getPassword() + Constant.SALT);
        user.setPassword(password);
        return insert(user);
    }

    @Override
    public User loginIn(String account, String password) {
        String type;
        if (Utils.isEmail(account)) {
            //邮箱登录
            type = "email";
        } else {
            //手机号登录
            type = "phone";
        }

        //帐号查询
        Map<String, String> map = new HashMap<>();
        map.put(type, account);
        map.put("password", Encrypt.string2SHA256(password + Constant.SALT));
        List<User> users = queryByColumns(map);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<UserTag> getUserTagByUserId(String userId, String tagClassify) {
        logger.error("getUserTagByUserId() 此方法未实现");
        return null;
    }

    @Override
    public int addUserTag(List<UserTag> userTags) {
        if (userTags.size() > 0) {
            return userRepository.insertUserTag(userTags);
        }
        return 0;
    }

    @Override
    public boolean existPhone(String phone) {
        if (!StringUtils.isEmpty(phone)) {
            List<User> users = queryByColumn("phone", phone, 0, 1);
            return users.size() > 0;
        }
        return false;
    }

    @Override
    public boolean existEmail(String email) {
        if (!StringUtils.isEmpty(email)) {
            List<User> users = queryByColumn("email", email, 0, 1);
            return users.size() > 0;
        }
        return false;
    }
}

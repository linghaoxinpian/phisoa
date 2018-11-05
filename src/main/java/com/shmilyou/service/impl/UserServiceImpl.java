package com.shmilyou.service.impl;

import com.alibaba.fastjson.JSON;
import com.shmilyou.entity.OpenUser;
import com.shmilyou.entity.User;
import com.shmilyou.entity.UserTag;
import com.shmilyou.repository.UserRepository;
import com.shmilyou.service.UserService;
import com.shmilyou.service.bo.AppIdAndOpenIdAndAccessToken;
import com.shmilyou.utils.Constant;
import com.shmilyou.utils.Encrypt;
import com.shmilyou.utils.Utils;
import com.shmilyou.utils.WebUtils;
import com.shmilyou.web.controller.vo.QQUserInfo;
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

    @Override
    public int registerOpenUser(OpenUser openUser) {
        return userRepository.insertOpenUser(openUser);
    }

    @Override
    public OpenUser queryByOpenId(String openid) {
        if (!StringUtils.isEmpty(openid)) {
            return userRepository.queryByOpenId(openid);
        }
        return null;
    }

    @Override
    public User QQLogin(AppIdAndOpenIdAndAccessToken qqLoginHelper) {
        //1检查是否第一次登录网站
        OpenUser exit = queryByOpenId(qqLoginHelper.getOpenid());
        if (exit != null) {
            logger.info("QQ登录,非第一次登录：" + exit.toString());
            return queryById(exit.getUserId());
        } else {
            logger.info("QQ登录,第一次登录：" + qqLoginHelper);
            //2.获取个人信息
            String userInfo = WebUtils.getJsonFromUrl("https://graph.qq.com/user/get_user_info?access_token=" + qqLoginHelper.getAccess_token() + "&oauth_consumer_key=" + qqLoginHelper.getClient_id() + "&openid=" + qqLoginHelper.getOpenid());
            QQUserInfo qqUserInfo = JSON.parseObject(userInfo, QQUserInfo.class);
            logger.info("QQ返回的数据：" + qqUserInfo.toString());
            if (StringUtils.isEmpty(qqUserInfo.getNickname())) {
                return null;
            }
            //3.先插入【User表】
            User user = new User();
            user.setNickname(qqUserInfo.getNickname());
            user.setGender("男".equals(qqUserInfo.getGender()) ? 0 : 1);
            insert(user);
            //4.插入第三方登录表
            OpenUser openUser = new OpenUser();
            openUser.setUserId(user.getId());
            openUser.setOpenType("QQ");
            openUser.setOpenId(qqLoginHelper.getOpenid());
            openUser.setAccessToken(qqLoginHelper.getAccess_token());
            openUser.setNickname(qqUserInfo.getNickname());
            openUser.setAvatar(qqUserInfo.getFigureurl_qq_1());
            registerOpenUser(openUser);
            return user;
        }
    }
}

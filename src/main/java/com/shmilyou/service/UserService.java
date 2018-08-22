package com.shmilyou.service;

import com.shmilyou.entity.User;
import com.shmilyou.entity.UserTag;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/15
 */
public interface UserService extends BaseService<User> {

    //注册
    int register(User user);

    //登录

    /**
     * 根据手机号、邮箱登录
     *
     * @param account  手机号、邮箱登录
     * @param password 密码，需加盐处理
     * @return
     */
    User loginIn(String account, String password);

    /**
     * 加载用户[特长标签or爱好标签]
     *
     * @param tagClassify 标签分类
     * @return
     */
    List<UserTag> getUserTagByUserId(String userId, String tagClassify);
}

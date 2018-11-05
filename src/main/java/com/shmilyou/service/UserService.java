package com.shmilyou.service;

import com.shmilyou.entity.OpenUser;
import com.shmilyou.entity.User;
import com.shmilyou.entity.UserTag;
import com.shmilyou.service.bo.AppIdAndOpenIdAndAccessToken;

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

    /**
     * 给用户添加标签/特长（特长估计用不上）
     * <p>userTags.id请设为null</p>
     *
     * @return 批量执行成功条数
     */
    int addUserTag(List<UserTag> tags);

    /**
     * 是否该手机已存在
     */
    boolean existPhone(String phone);

    /**
     * 是否该邮箱已存在
     */
    boolean existEmail(String email);

    /** 注册第三方登录 */
    int registerOpenUser(OpenUser openUser);

    /** 查询【第三方登录表】 */
    OpenUser queryByOpenId(String openid);

    /** QQ登录 */
    User QQLogin(AppIdAndOpenIdAndAccessToken qqLoginHelper);
}

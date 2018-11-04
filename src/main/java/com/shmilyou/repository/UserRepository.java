package com.shmilyou.repository;

import com.shmilyou.entity.OpenUser;
import com.shmilyou.entity.User;
import com.shmilyou.entity.UserTag;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/15
 */
public interface UserRepository extends BaseRepository<User> {

    /**
     * 给用户添加标签/特长（特长估计用不上）
     * <p>userTags.id请设为null</p>
     *
     * @return 批量执行成功条数
     */
    int insertUserTag(List<UserTag> userTags);

    /** 新增第三方登录 */
    int insertOpenUser(OpenUser openUser);
}

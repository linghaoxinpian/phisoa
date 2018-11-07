package com.shmilyou.service.impl;
/* Created with 岂止是一丝涟漪     530060499@qq.com    2018/11/7 */

import com.shmilyou.entity.UserCollectCourse;
import com.shmilyou.repository.UserCollectCourseRepository;
import com.shmilyou.service.UserCollectCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCollectCourseServiceImpl extends BaseServiceImpl<UserCollectCourse> implements UserCollectCourseService {

    @Autowired
    UserCollectCourseServiceImpl(UserCollectCourseRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<UserCollectCourse> queryByUserId(String userId) {
        return queryByColumn("userId", userId).stream()
                .sorted(Comparator.comparing(UserCollectCourse::getAddTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public UserCollectCourse removeCollectCourseById(String userId, String collectId) {
        UserCollectCourse exit = queryById(collectId);
        if (exit != null) {
            if (exit.getUserId().equals(userId)) {
                delete(collectId);
                return exit;
            }
            logger.error("用户id：" + userId + ",正在非法删除非自身收藏");
        }
        return null;
    }
}

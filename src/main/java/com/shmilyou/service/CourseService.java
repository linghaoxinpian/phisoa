package com.shmilyou.service;

import com.shmilyou.entity.Course;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
public interface CourseService extends BaseService<Course> {

    /**
     * 根据分类加载课程
     *
     * @param tagId
     * @return
     */
    List<Course> queryByTagId(String tagId, int pageIndex, int pageSize);
}

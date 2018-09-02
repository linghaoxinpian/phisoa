package com.shmilyou.service;

import com.shmilyou.entity.OpenCourse;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
public interface OpenCourseService extends BaseService<OpenCourse> {

    List<OpenCourse> queryByTagId(String tagId, int pageIndex, int pageSize);
}

package com.shmilyou.service;

import com.shmilyou.entity.Course;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
public interface CourseService extends BaseService<Course> {

    /**
     * 根据单个标签加载课程
     *
     * @param tagId
     * @return
     */
    List<Course> queryByTagId(String tagId, int pageIndex, int pageSize);

    /**
     * 根据名称搜索
     * <p>后续必须改为【分词查询】</p>
     *
     * @param courseName
     * @return
     */
    List<Course> queryByName(String courseName, int pageIndex, int pageSize);

    /**
     * 根据多个标签Id加载课程
     *
     * @param tagIds
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<Course> queryByTagIds(List<String> tagIds, int pageIndex, int pageSize);

    /**
     * 加载课程，根据【标签】及【其子标签】
     *
     * @param tagId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<Course> loadCourseByTagAndChildTag(String tagId, int pageIndex, int pageSize);

    /**
     * 机构主页显示的课程，默认添加时间排序
     */
    List<Course> loadHomeCourse(String organizationId, int pageIndex, int pageSize);
}

package com.shmilyou.service;

import com.shmilyou.entity.Course;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
public interface CourseService extends BaseService<Course> {

    /**
     * 根据单个标签加载课程
     */
    List<Course> queryByTagId(String tagId, int pageIndex, int pageSize);

    /**
     * 根据名称搜索
     * <p>后续必须改为【分词查询】</p>
     */
    List<Course> queryByName(String courseName, int pageIndex, int pageSize);

    /**
     * 根据多个标签Id加载课程
     */
    List<Course> queryByTagIds(List<String> tagIds, int pageIndex, int pageSize);

    /**
     * 加载课程，根据【标签】及【其子标签】
     *
     * @return
     */
    List<Course> loadByTagAndChildTag(String tagId, int pageIndex, int pageSize);

    /**
     * 机构主页显示的课程，默认添加时间排序
     */
    List<Course> loadHomeCourse(String organizationId, int pageIndex, int pageSize);

    /**
     * 根据机构与标签加载
     */
    List<Course> loadByOrganizationAndTag(String organizationId, String tagId);

    /**
     * 根据机构与标签加载相似课程
     */
    List<Course> loadByOrganizationAndTagAndChildTag(String organizationId, String tagId);

    /**
     * 根据机构与多个标签加载相似课程
     */
    List<Course> loadByOrganizationAndTags(String organizationId, List<String> tagIds);
}

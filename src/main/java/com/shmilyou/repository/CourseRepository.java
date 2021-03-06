package com.shmilyou.repository;

import com.shmilyou.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
public interface CourseRepository extends BaseRepository<Course> {

    /**
     * 根据多个标签Id加载分类
     *
     * @param tagIds
     * @return
     */
    List<Course> queryByTagIds(@Param("tagIds") List<String> tagIds, @Param("pageIndex") int pageIndex,
                               @Param("pageSize") int pageSize);

    /**
     * 根据机构id与多个标签加载课程
     */
    List<Course> queryByOrganizationAndTags(@Param("organizationId") String organizationId,
                                            @Param("tagIds") List<String> tagIds);

    List<Course> queryByOrganizationAndTag(@Param("organizationId") String organizationId, @Param("tagId") String tagId);

    /** 模糊查询【名称】 */
    List<Course> queryByNameForLike(@Param("courseName") String courseName, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
}

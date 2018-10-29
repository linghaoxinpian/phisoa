package com.shmilyou.service.impl;

import com.shmilyou.entity.Category;
import com.shmilyou.entity.Course;
import com.shmilyou.repository.CourseRepository;
import com.shmilyou.service.CategoryService;
import com.shmilyou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
@Service
public class CourseServiceImpl extends BaseServiceImpl<Course> implements CourseService {

    @Autowired
    CourseServiceImpl(CourseRepository baseRepository) {
        super(baseRepository);
    }

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Course> queryByTagId(String tagId, int pageIndex, int pageSize) {
        return queryByColumn("categoryId", tagId, pageIndex, pageSize);
    }

    @Override
    public List<Course> queryByName(String courseName, int pageIndex, int pageSize) {
        return queryByColumn("name", courseName, pageIndex, pageSize);
    }

    @Override
    public List<Course> queryByTagIds(List<String> tagIds, int pageIndex, int pageSize) {
        return courseRepository.queryByTagIds(tagIds, pageIndex, pageSize);
    }

    @Override
    public List<Course> loadByTagAndChildTag(String tagId, int pageIndex, int pageSize) {
        Category category = categoryService.queryById(tagId);
        List<Course> courses = new ArrayList<>();
        if (category != null) {
            if (category.getLevel().equals(Category.level2)) {
                //若是2级分类，直接加载
                courses = queryByTagId(category.getId(), pageIndex, pageSize);
            } else {
                //若是1级分类，递归加载
                List<Category> level2 = categoryService.queryByParentId(category.getId());
                level2.add(category);
                courses = queryByTagIds(level2.stream().map(Category::getId).collect(Collectors.toList()), pageIndex, pageSize);
            }
        }
        return courses;
    }

    @Override
    public List<Course> loadHomeCourse(String organizationId, int pageIndex, int pageSize) {
        List<Course> source = queryByColumn("ownerId", organizationId, pageIndex, pageSize);
        List<Course> courses = source.stream().sorted(Comparator.comparing(Course::getAddTime).reversed()).collect(Collectors.toList());
        return courses;
    }

    @Override
    public List<Course> loadByOrganizationAndTag(String organizationId, String tagId) {
        return courseRepository.queryByOrganizationAndTag(organizationId, tagId);
    }

    @Override
    public List<Course> loadByOrganizationAndTagAndChildTag(String organizationId, String tagId) {
        Category category = categoryService.queryById(tagId);
        List<Course> courses = Collections.emptyList();
        if (category != null) {
            if (category.getLevel().equals(Category.level2)) {
                //若是2级分类，直接加载
                courses = loadByOrganizationAndTag(organizationId, tagId);
            } else {
                //若是1级分类，递归加载
                List<Category> level2 = categoryService.queryByParentId(category.getId());
                level2.add(category);
                courses = loadByOrganizationAndTags(organizationId, level2.stream().map(Category::getId).collect(Collectors.toList()));
            }
        }
        return courses;
    }

    @Override
    public List<Course> loadByOrganizationAndTags(String organizationId, List<String> tagIds) {
        return courseRepository.queryByOrganizationAndTags(organizationId, tagIds);
    }

    @Override
    public List<Course> queryByOrganizationId(String organizationId, int pageIndex, int pageSize) {
        return queryByColumn("ownerId", organizationId, pageIndex, pageSize);
    }

}

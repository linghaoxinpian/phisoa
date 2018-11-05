package com.shmilyou.service;

import com.shmilyou.entity.Lecturer;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
public interface LecturerService extends BaseService<Lecturer> {

    /**
     * 根据机构Id获取
     */
    List<Lecturer> queryByOrganizationId(String organizationId, int pageIndex, int pageSize);

    /** 获取机构全部讲师 */
    List<Lecturer> queryByOrganizationId(String organizationId);

    /** 查询的同时可以进行校验 */
    Lecturer loadByOrganizationIdAndLecturerId(String organizationId, String lecturerId);
}

package com.shmilyou.repository;

import com.shmilyou.entity.Lecturer;
import org.apache.ibatis.annotations.Param;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
public interface LecturerRepository extends BaseRepository<Lecturer> {

    /** 查询某机构的某一位讲师 */
    Lecturer queryByOrganizationIdAndLecturerId(@Param("organizationId") String organizationId, @Param("lecturerId") String lecturerId);
}

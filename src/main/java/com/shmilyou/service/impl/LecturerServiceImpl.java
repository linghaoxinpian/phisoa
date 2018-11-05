package com.shmilyou.service.impl;

import com.shmilyou.entity.Lecturer;
import com.shmilyou.repository.LecturerRepository;
import com.shmilyou.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
@Service
public class LecturerServiceImpl extends BaseServiceImpl<Lecturer> implements LecturerService {

    @Autowired
    LecturerServiceImpl(LecturerRepository baseRepository) {
        super(baseRepository);
    }

    @Autowired
    private LecturerRepository lecturerRepository;

    @Override
    public List<Lecturer> queryByOrganizationId(String organizationId, int pageIndex, int pageSize) {
        return queryByColumn("organizationId", organizationId, pageIndex, pageSize);
    }

    @Override
    public List<Lecturer> queryByOrganizationId(String organizationId) {
        return queryByColumn("organizationId", organizationId);
    }

    @Override
    public Lecturer loadByOrganizationIdAndLecturerId(String organizationId, String lecturerId) {
        return lecturerRepository.queryByOrganizationIdAndLecturerId(organizationId, lecturerId);
    }

    @Override
    public int deleteByOrganizationIdAndLecturerId(String organizationId, String lecturerId) {
        Lecturer lecturer = queryById(lecturerId);
        if (organizationId.equals(lecturer.getOrganizationId())) {
            return delete(lecturerId);
        }
        return -1;
    }
}

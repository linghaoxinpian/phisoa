package com.shmilyou.service.impl;

import com.shmilyou.entity.Lecturer;
import com.shmilyou.repository.LecturerRepository;
import com.shmilyou.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
@Service
public class LecturerServiceImpl extends BaseServiceImpl<Lecturer> implements LecturerService {

    @Autowired
    LecturerServiceImpl(LecturerRepository baseRepository) {
        super(baseRepository);
    }
}

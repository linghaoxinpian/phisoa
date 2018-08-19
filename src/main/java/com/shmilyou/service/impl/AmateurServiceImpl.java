package com.shmilyou.service.impl;

import com.shmilyou.entity.Amateur;
import com.shmilyou.repository.AmateurRepository;
import com.shmilyou.service.AmateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
@Service
public class AmateurServiceImpl extends BaseServiceImpl<Amateur> implements AmateurService {

    @Autowired
    AmateurServiceImpl(AmateurRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Amateur> searchByName(String amateurName) {
        return null;
    }

    @Override
    public List<Amateur> searchByPlace(String place) {
        return null;
    }

    @Override
    public List<Amateur> searchByTag(String tagName, String pageIndex, String pageSize) {
        return null;
    }
}

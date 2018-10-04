package com.shmilyou.service.impl;

import com.shmilyou.entity.Area;
import com.shmilyou.repository.AreaRepository;
import com.shmilyou.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/22
 */
@Service
public class AreaServiceImpl extends BaseServiceImpl<Area> implements AreaService {

    @Autowired
    AreaServiceImpl(AreaRepository baseRepository) {
        super(baseRepository);
    }

    @Autowired
    private AreaRepository areaRepository;

    //-------------------方法区-------------------

    @Override
    public Area queryByFullName(String fullAreaName) {
        return areaRepository.queryByFullName(fullAreaName);
    }
}

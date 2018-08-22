package com.shmilyou.service.impl;

import com.shmilyou.entity.Area;
import com.shmilyou.repository.AreaRepository;
import com.shmilyou.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/22
 */
public class AreaServiceImpl extends BaseServiceImpl<Area> implements AreaService {

    @Autowired
    AreaServiceImpl(AreaRepository baseRepository) {
        super(baseRepository);
    }
}

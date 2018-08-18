package com.shmilyou.service.impl;

import com.shmilyou.entity.Amateur;
import com.shmilyou.repository.AmateurRepository;
import com.shmilyou.service.AmateurService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
public class AmateurServiceImpl extends BaseServiceImpl<Amateur> implements AmateurService {

    @Autowired
    AmateurServiceImpl(AmateurRepository baseRepository) {
        super(baseRepository);
    }
}

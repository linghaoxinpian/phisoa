package com.shmilyou.service.impl;

import com.shmilyou.entity.UserTag;
import com.shmilyou.repository.UserTagRepository;
import com.shmilyou.service.UserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
@Service
public class UserTagServiceImpl extends BaseServiceImpl<UserTag> implements UserTagService {

    @Autowired
    UserTagServiceImpl(UserTagRepository baseRepository) {
        super(baseRepository);
    }

    //-------------------方法区-------------------
}

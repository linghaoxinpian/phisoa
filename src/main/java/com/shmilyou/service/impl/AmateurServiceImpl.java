package com.shmilyou.service.impl;

import com.shmilyou.entity.Amateur;
import com.shmilyou.repository.AmateurRepository;
import com.shmilyou.repository.AreaRepository;
import com.shmilyou.service.AmateurService;
import com.shmilyou.utils.Constant;
import com.shmilyou.utils.Encrypt;
import com.shmilyou.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
@Service
public class AmateurServiceImpl extends BaseServiceImpl<Amateur> implements AmateurService {

    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private AmateurRepository amateurRepository;

    @Autowired
    AmateurServiceImpl(AmateurRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Amateur> searchByName(String amateurName) {
        return queryByColumn("amateurName", amateurName);
    }

    @Override
    public List<Amateur> searchByPlace(String place) {
        return amateurRepository.queryByPlace(place);
    }

    @Override
    public List<Amateur> searchByTag(String tagName, int pageIndex, int pageSize) {

        return queryByColumn("tagName", tagName, pageIndex, pageSize);
    }

    @Override
    public Amateur loginIn(String account, String password) {
        if (Utils.isEmail(account)) {
            List<Amateur> amateurs = queryByColumns(new HashMap<String, String>() {
                {
                    put("email", account);
                    put("password", Encrypt.string2SHA256(password + Constant.SALT));
                }
            });
            if (amateurs.size() > 0) {
                return amateurs.get(0);
            }
        }
        return null;
    }
}

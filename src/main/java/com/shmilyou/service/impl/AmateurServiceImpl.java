package com.shmilyou.service.impl;

import com.shmilyou.entity.Amateur;
import com.shmilyou.entity.AmateurTag;
import com.shmilyou.repository.AmateurRepository;
import com.shmilyou.service.AmateurService;
import com.shmilyou.utils.Constant;
import com.shmilyou.utils.Encrypt;
import com.shmilyou.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
@Service
public class AmateurServiceImpl extends BaseServiceImpl<Amateur> implements AmateurService {

    @Autowired
    AmateurServiceImpl(AmateurRepository baseRepository) {
        super(baseRepository);
    }

    //-------------------方法区-------------------
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
        return amateurRepository.queryByTagName(tagName, pageIndex, pageSize);
    }

    @Override
    public Amateur loginIn(String account, String password) {
        String type;
        if (Utils.isEmail(account)) {
            //邮箱登录
            type = "email";
        } else {
            //手机号登录
            type = "phone";
        }

        //帐号查询
        Map<String, String> map = new HashMap<>();
        map.put(type, account);
        map.put("password", Encrypt.string2SHA256(password + Constant.SALT));
        List<Amateur> amateurs = queryByColumns(map);
        if (amateurs.size() > 0) {
            return amateurs.get(0);
        }
        return null;
    }

    @Override
    public int register(Amateur amateur) {
        return insert(amateur);
    }

    @Override
    public List<Amateur> queryByTagId(String tagId, int pageIndex, int pageSize) {
        return amateurRepository.queryByTagId(tagId, pageIndex, pageSize);
    }

    @Override
    public List<Amateur> indexRecommendTop() {
        return null;
    }

    @Override
    public int addAmateurTag(List<AmateurTag> amateurTags) {
        return amateurRepository.insertAmateurTag(amateurTags);
    }
}

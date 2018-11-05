package com.shmilyou.service.impl;

import com.shmilyou.entity.Area;
import com.shmilyou.repository.AreaRepository;
import com.shmilyou.service.AreaService;
import com.shmilyou.service.bo.AreaCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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

    @Override
    public List<Area> queryByParentId(Integer parentId) {
        return queryByColumn("parentId", parentId.toString());
    }

    @Override
    public String loadSelectAreaCode(AreaCode areaCode) {
        if (areaCode != null) {
            if (!StringUtils.isEmpty(areaCode.getDistrict())) {
                List<Area> districts = queryByColumn("areaCode", areaCode.getDistrict());
                if (districts.size() > 0) {
                    return areaCode.getDistrict();
                }
            }
            if (!StringUtils.isEmpty(areaCode.getCity())) {
                List<Area> city = queryByColumn("areaCode", areaCode.getCity());
                if (city.size() > 0) {
                    return areaCode.getCity();
                }
            }
            if (!StringUtils.isEmpty(areaCode.getCity())) {
                List<Area> provinces = queryByColumn("areaCode", areaCode.getProvince());
                if (provinces.size() > 0) {
                    return areaCode.getProvince();
                }
            }
        }
        return "";
    }
}

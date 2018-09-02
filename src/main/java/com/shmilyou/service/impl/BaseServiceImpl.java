package com.shmilyou.service.impl;

import com.shmilyou.entity.BaseEntity;
import com.shmilyou.repository.AmateurRepository;
import com.shmilyou.repository.BaseRepository;
import com.shmilyou.service.BaseService;
import com.shmilyou.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/14
 */

public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    /**
     * T的简单字节码
     */
    private String T_name;
    @Autowired
    private BaseRepository<T> baseRepository;

    {
        //获取T
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] types = parameterizedType.getActualTypeArguments();
        Class<?> clazz = (Class<?>) types[0];
        T_name = clazz.getSimpleName();
    }

    BaseServiceImpl(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Autowired
    protected AmateurRepository amateurRepository;

    //-------------------方法区-------------------
    @Override
    public int insert(T entity) {
        return baseRepository.insert(entity);

    }

    @Override
    public int delete(String id) {
        return baseRepository.delete(T_name, id);
    }

    @Override
    public int update(T entity) {
        return baseRepository.update(entity);
    }

    @Override
    public T queryById(String id) {
        return baseRepository.queryById(T_name, id);
    }

    @Override
    public List<T> queryByColumn(String column, String value) {
        column = Utils.camel2Underline(column);
        return baseRepository.queryByColumn(T_name, column, value);
    }

    @Override
    public List<T> queryByColumns(Map<String, String> columnsToValues) {
        Map<String, String> map2 = new HashMap<>();
        for (String column : columnsToValues.keySet()) {
            map2.put(Utils.camel2Underline(column), columnsToValues.get(column));
        }
        final String[] where = {" "};
        map2.forEach((column, value) -> {
            if (org.apache.commons.lang3.StringUtils.isNumeric(value)) {
                where[0] += column + " = " + value + " AND ";
            } else {
                where[0] += column + " = '" + value + "' AND ";
            }
        });

        return baseRepository.queryByColumns(T_name, where[0]);
    }

    @Override
    public List<T> queryByColumn(String column, String value, int pageIndex, int pageSize) {
        column = Utils.camel2Underline(column);
        return baseRepository.queryByColumnLimit(T_name, column, value, pageIndex, pageSize);
    }

    @Override
    public List<T> queryByColumns(Map<String, String> columnsToValues, int pageIndex, int pageSize) {
        Map<String, String> map2 = new HashMap<>();
        for (String column : columnsToValues.keySet()) {
            map2.put(Utils.camel2Underline(column), columnsToValues.get(column));
        }
        final String[] where = {" "};
        map2.forEach((column, value) -> {
            if (org.apache.commons.lang3.StringUtils.isNumeric(value)) {
                where[0] += column + " = " + value + " AND ";
            } else {
                where[0] += column + " = '" + value + "' AND ";
            }
        });

        return baseRepository.queryByColumnsLimit(T_name, where[0], pageIndex, pageSize);
    }

}

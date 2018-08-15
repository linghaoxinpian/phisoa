package com.shmilyou.service.impl;

import com.shmilyou.entity.IEntity;
import com.shmilyou.repository.BaseRepository;
import com.shmilyou.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/14
 */


public class BaseService<T extends IEntity> implements IBaseService<T> {

    /**
     * T的简单字节码
     */
    private String T_name;

    {
        //获取T
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] types = parameterizedType.getActualTypeArguments();
        Class<?> clazz = (Class<?>) types[0];
        T_name = clazz.getSimpleName();
    }

    @Autowired
    private BaseRepository<T> baseRepository;

    @Override
    public String insert(T entity) {
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
        return baseRepository.queryByColumn(T_name, column, value);
    }

    @Override
    public List<T> queryByColumns(Map<String, String> columnsToValues) {
        final String[] where = {" "};
        columnsToValues.forEach((column, value) -> {
            where[0] += column + "=" + value + " AND ";
        });
        return baseRepository.queryByColumns(T_name, where[0]);
    }

    @Override
    public T queryByColumns2(Map<String, String> columnsToValues) {
        final String[] where = {" "};
        columnsToValues.forEach((column, value) -> {
            where[0] += column + "=" + value + " AND ";
        });
        return baseRepository.queryByColumns2(T_name, where[0]);
    }
}

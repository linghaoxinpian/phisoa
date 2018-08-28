package com.shmilyou.service;

import com.shmilyou.entity.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/14
 */
public interface BaseService<T extends BaseEntity> {
    /**
     * @return 主键
     */
    int insert(T entity);

    /**
     * 软删除
     *
     * @param id 主键
     * @return 影响的行数
     */
    int delete(String id);

    /**
     * @return 影响的行数
     */
    int update(T entity);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    T queryById(String id);

    /**
     * 根据指定列查询结果集
     *
     * @param column 列名
     * @param value  值
     */
    List<T> queryByColumn(String column, String value);

    /**
     * 根据指定多列查询结果集
     *
     * @param columnsToValues map<列名,值>
     * @return
     */
    List<T> queryByColumns(Map<String, String> columnsToValues);

    /**
     * 根据指定列查询结果集
     *
     * @param column 列名
     * @param value  值
     */
    List<T> queryByColumn(String column, String value, int pageIndex, int pageSize);

    /**
     * 根据指定多列查询结果集
     *
     * @param columnsToValues map<列名,值>
     * @return
     */
    List<T> queryByColumns(Map<String, String> columnsToValues, int pageIndex, int pageSize);

}

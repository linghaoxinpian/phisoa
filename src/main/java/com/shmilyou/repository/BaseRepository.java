package com.shmilyou.repository;

import com.shmilyou.entity.BaseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/14
 */
public interface BaseRepository<T extends BaseEntity> {

//    /**
//     * @return 主键
//     */
//    @Deprecated
//    String insert(String tableName, String columns, String values);

    /**
     * @param entity
     * @return 影响的行数，主键会自动复制到对象id属性中
     */
    int insert(T entity);

    /**
     * 软删除
     *
     * @param id 主键
     * @return 影响的行数
     */
    int delete(@Param("tableName") String tableName, @Param("id") String id);

    /**
     * 由子类实现
     *
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    T queryById(@Param("tableName") String tableName, @Param("id") String id);

    /**
     * 根据指定列查询结果集
     *
     * @param column 列名
     * @param value  值
     */
    List<T> queryByColumn(@Param("tableName") String tableName, @Param("column") String column, @Param("value") String value);

    /**
     * 根据指定多列查询结果集
     *
     * @param columnsToValues map<列名,值>
     * @return
     */
    List<T> queryByColumns(@Param("tableName") String tableName, @Param("columnsToValues") String columnsToValues);

}

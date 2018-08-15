package com.shmilyou.repository;

import com.shmilyou.entity.IEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/14
 */
public interface BaseRepository<T extends IEntity> {

//    /**
//     * @return 主键
//     */
//    @Deprecated
//    String insert(String tableName, String columns, String values);

    /**
     * 由子类实现
     *
     * @param entity
     * @return
     */
    String insert(T entity);

    /**
     * 软删除
     *
     * @param id 主键
     * @return 影响的行数，success为1，error为-1
     */
    int delete(@Param("tableName") String tableName, @Param("id") String id);

//    /**
//     * @return 影响的行数，success为1，error为-1
//     */
//    @Deprecated
//    int update(String tableName, String columnsAndValues);

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


    /**
     * 根据指定多列查询结果
     *
     * @param columnsToValues map<列名,值>
     * @return
     */
    T queryByColumns2(@Param("tableName") String tableName, @Param("columnsToValues") String columnsToValues);

}

package com.fc.base.service;

import com.fc.base.entity.base.PagingData;

import java.util.List;

/**
 * @program: fc-mic
 * @description: This is a class
 * @author: fucheng.zou
 * @create: 2018-12-29 13:57
 **/
public interface BaseService<T> {

    /**
     * 新增实体信息
     * @param entity
     * @return
     */
    Integer save(T entity);

    /**
     * 根据主键ID，删除实体信息
     * @param entity
     * @return
     */
    Integer deleteById(T entity);

    /**
     * 根据主键ID，更新实体信息
     * @param entity
     * @return
     */
    Integer updateById(T entity);

    /**
     * 根据主键ID，获取实体信息
     * @param id
     * @return
     */
    T selectById(String id);

    /**
     * 查询单个实体信息
     * @param entity
     * @return
     */
    T selectOne(T entity);

    /**
     * 查询符合条件的实体总数
     * @param entity
     * @return
     */
    Integer selectByIndexCount(T entity);

    /**
     * 查询符合条件的实体
     * @param entity
     * @return
     */
    List<T> selectByIndex(T entity);

    /**
     * 获取分页实体信息
     * @param entity
     * @return
     */
    PagingData<T> selectPage(T entity);
}

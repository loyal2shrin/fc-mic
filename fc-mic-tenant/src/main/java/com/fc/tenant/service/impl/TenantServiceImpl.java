package com.fc.tenant.service.impl;

import com.fc.base.entity.base.PagingData;
import com.fc.tenant.bean.domain.Tenant;
import com.fc.tenant.service.TenantService;
import com.fc.tenant.service.mapper.TenantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: fc-mic
 * @description: This is a class
 * @author: fucheng.zou
 * @create: 2018-12-29 13:49
 **/
@Service
public class TenantServiceImpl implements TenantService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TenantServiceImpl.class);

    @Autowired
    private TenantMapper tenantMapper;

    /**
     * 新增实体信息
     *
     * @param entity
     * @return
     */
    @Override
    public Integer save(Tenant entity) {
        return tenantMapper.save(entity);
    }

    /**
     * 根据主键ID，删除实体信息
     *
     * @param entity
     * @return
     */
    @Override
    public Integer deleteById(Tenant entity) {
        return tenantMapper.deleteById(entity);
    }

    /**
     * 根据主键ID，更新实体信息
     *
     * @param tenant
     * @return
     */
    @Override
    public Integer updateById(Tenant tenant) {
        if (null == tenant || null == tenant.getId()) {
            LOGGER.warn("update tenant, but tenant is null  or tenant id is null...");
            return 0;
        }
        return tenantMapper.updateById(tenant);
    }

    /**
     * 根据主键ID，获取实体信息
     *
     * @param id
     * @return
     */
    @Override
    public Tenant selectById(String id) {
        return tenantMapper.selectById(id);
    }

    /**
     * 查询单个实体信息
     *
     * @param entity
     * @return
     */
    @Override
    public Tenant selectOne(Tenant entity) {
        return tenantMapper.selectOne(entity);
    }

    /**
     * 查询符合条件的实体总数
     *
     * @param entity
     * @return
     */
    @Override
    public Integer selectByIndexCount(Tenant entity) {
        return tenantMapper.selectByIndexCount(entity);
    }

    /**
     * 查询符合条件的实体
     *
     * @param entity
     * @return
     */
    @Override
    public List<Tenant> selectByIndex(Tenant entity) {
        return tenantMapper.selectByIndex(entity);
    }

    /**
     * 获取分页实体信息
     *
     * @param tenant
     * @return
     */
    @Override
    public PagingData<Tenant> selectPage(Tenant tenant) {
        PagingData<Tenant> pagingData = new PagingData<Tenant>();
        if (null == tenant) {
            LOGGER.warn("select apiInfo page, but apiInfo is null...");
            return pagingData;
        }
        Integer queryCount = tenantMapper.selectByIndexCount(tenant);
        pagingData.setTotal(queryCount);
        if (null != queryCount && queryCount <= 0) {
            LOGGER.info("select apiInfo page , but count {} == 0 ...", queryCount);
            return pagingData;
        }
        List<Tenant> tenants = selectByIndex(tenant);
        pagingData.setRows(tenants);
        return pagingData;
    }
}

package com.fc.tenant.service.mapper;

import com.fc.base.repository.BaseMapper;
import com.fc.tenant.bean.domain.Tenant;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: fc-mic
 * @description: This is a class
 * @author: fucheng.zou
 * @create: 2018-12-29 13:47
 **/
@Mapper
public interface TenantMapper extends BaseMapper<Tenant> {
}

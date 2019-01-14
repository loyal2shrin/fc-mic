package com.fc.provider.service.mapper;

import com.fc.api.bean.domain.Tenant;
import com.fc.base.repository.BaseMapper;
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

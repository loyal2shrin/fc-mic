package com.fc.tenant.bean.domain;

import com.fc.base.entity.base.BaseEntity;
import com.fc.base.entity.base.IdEntity;

/**
 * @program: fc-mic
 * @description: This is a class
 * @author: fucheng.zou
 * @create: 2018-12-29 13:47
 **/
public class Tenant extends BaseEntity {

    private String tenantCode;

    private String tenantName;

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
}

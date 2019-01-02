package com.fc.base.entity.base;

import java.util.Date;
import java.util.List;

public abstract class OperateEntity extends IdEntity {
	
	private static final long serialVersionUID = 6339468883299171311L;
	
	public static final Integer STATUS_NORMAL = 0;
	public static final Integer STATUS_DELETE = 1;

	protected Integer status = 0;
    
	protected String tenantCode;
	
	protected List<String> companyCodes;
    protected List<String> siteCodes;
	protected List<String> whCodes;
	

	protected Date createTime;
	protected String createUserId;
	
	protected Date updateTime;
	protected String updateUserId;

	protected String remark;
	
	public List<String> getCompanyCodes() {
		return companyCodes;
	}

	public void setCompanyCodes(List<String> companyCodes) {
		this.companyCodes = companyCodes;
	}

	public List<String> getSiteCodes() {
		return siteCodes;
	}

	public void setSiteCodes(List<String> siteCodes) {
		this.siteCodes = siteCodes;
	}

	public List<String> getWhCodes() {
		return whCodes;
	}

	public void setWhCodes(List<String> whCodes) {
		this.whCodes = whCodes;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}

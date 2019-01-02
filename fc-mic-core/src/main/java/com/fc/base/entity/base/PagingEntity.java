package com.fc.base.entity.base;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PagingEntity extends OperateEntity {

	private static final long serialVersionUID = 2442811476914489832L;

	private Integer offset = 0;

	private Integer limit = 15;

	private String orderByType = "asc";

	private String orderBy = "1";
	
	private Date startTime;
	
	private Date endTime;
	
	@JsonIgnore
	public String getOrderByType() {
		return orderByType;
	}

	public void setOrderByType(String orderByType) {
		this.orderByType = orderByType;
	}

	@JsonIgnore
	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	@JsonIgnore
	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	@JsonIgnore
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	@JsonIgnore
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@JsonIgnore
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}

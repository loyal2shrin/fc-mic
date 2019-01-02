package com.fc.base.entity.base;

import java.io.Serializable;

public abstract class IdEntity implements Serializable {

  private static final long serialVersionUID = -4674398859076520064L;

    protected String id;
  
    protected Integer recVer;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRecVer() {
		return recVer;
	}

	public void setRecVer(Integer recVer) {
		this.recVer = recVer;
	}
}

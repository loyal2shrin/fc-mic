package com.fc.base.entity.base;

public class Response extends Model {

	private static final long serialVersionUID = 8775508366956939838L;

	private String code = "0";

	public Response(){
    }
	
	public Response(String code){
	  this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}

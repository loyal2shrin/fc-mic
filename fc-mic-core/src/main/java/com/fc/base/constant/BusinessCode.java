package com.fc.base.constant;

public enum BusinessCode {

	SUCCESS("000000"), 
	RUNNING("000001"), 
	FAIL("000002"), 
	LOGIN_FAIL("000007"), 
	NOT_RIGHT("000008"), 
	SESSION_TIMEOUT("000009"), 
	
	DENY_ACCESS("000010"),
	OUT_DOMAIN("000011"),
	BAD_SQL_ERROR("000012"), 
	NOT_EXIST("000013"),
	NEED_TOKEND("000014"),
	BAD_AUTH("000015"),
	ALREADY_EXIST("000016"),
	BAD_FILE("000017"),
	FILE_UPLOAD_ERROR("000018"),
	
	
	REST_METHOD_NOT_SUPPORT("000019"), 
	VALIDATE_ERROR("000020"), 
	PARAMETER_NULL("000021"), 
	PARAMETER_FORMAT_ERROR("000022"), 
	DUBBO_RPC_ERROR("000023"),
	
	
	RF_SCAN_REAPET("000024"),
	RF_NOT_EXIST("000025"),
	RF_INFO_FINISH("000026"),
	
	SERVER_ERROR("000500"),
	SERVICE_INVOKE_EXCEPTION("000999"),
	
	ORDER_API_ERROR("010000");
	
	

	private BusinessCode(String code) {
		this.code = code;
	}

	private String code;

	public String getCode() {
		return code;
	}
}
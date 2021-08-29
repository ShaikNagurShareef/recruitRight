package com.gcp.recruitRight.models;

public class BaseResponse {
	
	private User user;
	private Boolean booleanMsg;
	private String exceptionMessage;
	
	public BaseResponse() {
		
	}
	public BaseResponse(User user, Boolean booleanMsg, String exceptionMessage) {
		super();
		this.user = user;
		this.booleanMsg = booleanMsg;
		this.exceptionMessage = exceptionMessage;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Boolean getBooleanMsg() {
		return booleanMsg;
	}
	public void setBooleanMsg(Boolean booleanMsg) {
		this.booleanMsg = booleanMsg;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	
	
	
}

package com.gcp.recruitRight.response;

import com.gcp.recruitRight.models.User;

public class BaseResponse {
	
	private User user;
	private Boolean booleanMsg;
	private String exceptionMessage;
	private String sessionId;
	
	public BaseResponse() {
		
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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

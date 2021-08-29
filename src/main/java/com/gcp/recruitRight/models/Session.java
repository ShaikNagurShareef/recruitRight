package com.gcp.recruitRight.models;

public class Session{
	
	private static String userId;
	private static String sessionId;
	
	public static String getSessionId() {
		return sessionId;
	}

	public static void setSessionId(String sessionId) {
		Session.sessionId = sessionId;
	}

	public static String getUserId() {
		return userId;
	}

	public static void setUserId(String userId) {
		Session.userId = userId;
	}
	
	
	
}

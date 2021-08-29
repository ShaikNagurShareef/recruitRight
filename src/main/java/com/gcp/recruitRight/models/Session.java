package com.gcp.recruitRight.models;

public class Session{
	
	private static String sessionId;
	
	public Session() {
		
	}

	public Session(String sessionId) {
		super();
		Session.sessionId = sessionId;
	}

	public static String getSessionId() {
		return sessionId;
	}

	public static void setSessionId(String sessionId) {
		Session.sessionId = sessionId;
	}
	
	public static String getUsername() {
		String array[]=sessionId.split("#");
		return array[0];
	}
	
}

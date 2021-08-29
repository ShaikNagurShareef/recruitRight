package com.gcp.recruitRight.Impls;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class SessionManagement {
	
	private HashMap<String,String> sessions = new HashMap<String, String>();
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void addSession(String userId, String sessionId)
	{
		sessions.put(userId, sessionId);
		String sql = "INSERT into SESSION(userId,sessionId) values(?,?)";
		jdbcTemplate.update(sql,userId,sessionId);
		
	}
	public void removeSession(String userId)
	{
		sessions.remove(userId);
		String sql = "DELETE from SESSION where userId=?";
		jdbcTemplate.update(sql,userId);
	}
	public String getSessionId(String userId)
	{
		return sessions.get(userId);
	}
	public String getUsername(String sessionId)
	{
		String array[] = sessionId.split("#");
		return array[0];
	}

}

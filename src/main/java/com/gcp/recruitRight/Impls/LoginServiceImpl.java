package com.gcp.recruitRight.Impls;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcp.recruitRight.Repository.LoginServiceRepository;
import com.gcp.recruitRight.models.User;

@Service
public class LoginServiceImpl {
	
	@Autowired
	private LoginServiceRepository loginServiceRepository;
	@Autowired
	private SessionManagement sessionManagement;
	
	public boolean signup(User user) throws Exception
	{
		return loginServiceRepository.signup(user);
	}
	
	public String login(User user) throws Exception
	{
		if(loginServiceRepository.verifyUser(user))
		{
			Date dt = new Date();
			String sessionId = user.getUserId()+"#"+dt.getTime();
			sessionManagement.addSession(user.getUserId(),sessionId);
			return sessionId;
		}
		return null;	
	}
	
	public boolean logout(String userId)
	{
		sessionManagement.removeSession(userId);
		return true;
	}
	
	public boolean validate(String userId, String sessionId) throws Exception
	{
		if(sessionManagement.getSessionId(userId).equals(sessionId))
			return true;
		else
			throw new Exception("Invalid session....Please login");
	}
}

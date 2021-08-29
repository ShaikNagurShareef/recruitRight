package com.gcp.recruitRight.Impls;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcp.recruitRight.Repository.LoginServiceRepository;
import com.gcp.recruitRight.Requests.LoginServiceRequest;
import com.gcp.recruitRight.models.User;

@Service
public class LoginServiceImpl {
	
	@Autowired
	private LoginServiceRepository loginServiceRepository;
	@Autowired
	private SessionManagement sessionManagement;
	
	public boolean signup(LoginServiceRequest loginServiceRequest) throws Exception
	{
		List<User> users = loginServiceRepository.findUsers(loginServiceRequest);
		if(users.size()!=0) {
			for(User u : users)
			{
				if(u.getUserId().equals(loginServiceRequest.getUserId()))
					throw new Exception("Email already Registered....Please SignIn to continue");
			}	
		}
		int status = loginServiceRepository.insertIntoUser(loginServiceRequest);
		if(status == 1)
 			return true;
 		return false;
	}
	
	public String login(LoginServiceRequest loginServiceRequest) throws Exception
	{
		if(loginServiceRepository.verifyUser(loginServiceRequest))
		{
			Date dt = new Date();
			String sessionId = loginServiceRequest.getUserId()+"#"+dt.getTime();
			sessionManagement.addSession(loginServiceRequest.getUserId(),sessionId);
			return sessionId;
		}
		return null;	
	}
	
	public boolean logout(LoginServiceRequest loginServiceRequest) throws Exception
	{
		String userId = SessionManagement.getUserId(loginServiceRequest.getSessionId());
		try {
		if(validate(userId,loginServiceRequest.getSessionId()))
			sessionManagement.removeSession(userId);
			return true;
		} catch(Exception e) {
			throw new Exception("Invalid session");
		}
	}
	
	public boolean validate(String userId, String sessionId) throws Exception
	{
		if(SessionManagement.getSessionId(userId).equals(sessionId)) {
			return true;
		}	
		else {
			throw new Exception("Invalid session...");
		}
			
	}
}

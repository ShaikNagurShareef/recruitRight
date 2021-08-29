package com.gcp.recruitRight.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gcp.recruitRight.Impls.LoginServiceImpl;
import com.gcp.recruitRight.models.BaseResponse;
import com.gcp.recruitRight.models.Session;
import com.gcp.recruitRight.models.User;

@RestController
@CrossOrigin(origins="http://localhost:8080")
public class LoginServiceController {
	
	@Autowired
	private LoginServiceImpl loginServiceImpl; 
	
	@PostMapping("/signup")
	public ResponseEntity<BaseResponse> signup(@RequestBody User user) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			baseResponse.setBooleanMsg(loginServiceImpl.signup(user));
		} catch(Exception e) {
			baseResponse.setExceptionMessage(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(baseResponse);
	}
	
	@GetMapping("/login")
	public ResponseEntity<BaseResponse> login(@RequestBody User user) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			String sessionId = loginServiceImpl.login(user);
			if(sessionId!=null) {
				Session.setSessionId(sessionId);
				baseResponse.setBooleanMsg(true);
			}
			else
				baseResponse.setBooleanMsg(false); 
		}
		catch(Exception e) {
			baseResponse.setExceptionMessage(e.getMessage());
		}
		return ResponseEntity.ok(baseResponse);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<BaseResponse> logout(){
		BaseResponse baseResponse = new BaseResponse();
		if(loginServiceImpl.logout(Session.getUsername()))
			baseResponse.setBooleanMsg(true);
		else
			baseResponse.setBooleanMsg(false);
		return ResponseEntity.ok(baseResponse);
	}
	
}

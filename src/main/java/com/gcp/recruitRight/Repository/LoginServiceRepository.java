package com.gcp.recruitRight.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcp.recruitRight.Requests.LoginServiceRequest;
import com.gcp.recruitRight.models.User;

@Repository
public class LoginServiceRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<User> findUsers(LoginServiceRequest loginServiceRequest){
		String sql = "SELECT * from USER";
		List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
		return users;
	}
	
	public int insertIntoUser(LoginServiceRequest loginServiceRequest) throws Exception{
		try {
		String query="INSERT into USER(userId,userType,firstName,lastName,contact,password) values(?,?,?,?,?,?)";
		return jdbcTemplate.update(query,loginServiceRequest.getUserId(),loginServiceRequest.getUserType(),loginServiceRequest.getFirstName(),loginServiceRequest.getLastName(),loginServiceRequest.getContact(),loginServiceRequest.getPassword());
		} catch(Exception e) {
			throw new Exception("Error while Signing Up...");
		}
	}
	
	public boolean verifyUser(LoginServiceRequest loginServiceRequest) throws Exception
	{
		List<User> users = jdbcTemplate.query("SELECT * FROM USER where userId=?", new BeanPropertyRowMapper(User.class), loginServiceRequest.getUserId());
		if(users.size()==0)
			throw new Exception("Email not registered...Please SignUp to continue");
		for(User u:users)
			if(u.getPassword().equals(loginServiceRequest.getPassword()))
				return true;
		throw new Exception("Please enter correct password!");
	}

}

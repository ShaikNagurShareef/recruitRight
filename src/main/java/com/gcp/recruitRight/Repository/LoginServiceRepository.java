package com.gcp.recruitRight.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.gcp.recruitRight.models.User;

@Repository
public class LoginServiceRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public boolean signup(User user) throws Exception{
		try {
			String query="insert into user(userId,userType,firstName,lastName,contact,password) values(?,?,?,?,?,?)";
			int insert = jdbcTemplate.update(query,user.getUserId(),user.getUserType(),user.getFirstName(),user.getLastName(),user.getContact(),user.getPassword());
	 		if(insert == 1)
	 			return true;
		} catch(Exception e) {
			throw new Exception("Signup Unsuccessful");
		}
 		return false;
	}
	
	public boolean verifyUser(User user) throws Exception
	{
		try {
			List<User> users = jdbcTemplate.query("SELECT * FROM USER where userId=?", new BeanPropertyRowMapper(User.class), user.getUserId());
			if(users.size()==0)
				return false;
			for(User u:users)
				if(u.getPassword().equals(user.getPassword()))
					return true;
		} catch(Exception e) {
			throw new Exception("Invalid User");
		}
		return false;
		
	}

}

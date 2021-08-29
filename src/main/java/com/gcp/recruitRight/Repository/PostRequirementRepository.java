package com.gcp.recruitRight.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcp.recruitRight.Impls.SessionManagement;
import com.gcp.recruitRight.Requests.PostRequirementRequest;

@Repository
public class PostRequirementRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	SessionManagement sessionManagement;
	
	public int postRequirement(PostRequirementRequest postRequirementRequest) {
		
		String sql = "INSERT INTO REQUIREMENTS(userId,domain,jobRole,jobRoleType,techStack,experience) values(?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,postRequirementRequest.getSessionId(),postRequirementRequest.getDomain(),postRequirementRequest.getJobRole(),postRequirementRequest.getJobRoleType(),postRequirementRequest.getTechStack(),postRequirementRequest.getExperience());
	}
	
}

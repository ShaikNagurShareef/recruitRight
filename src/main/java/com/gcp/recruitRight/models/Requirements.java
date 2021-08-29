package com.gcp.recruitRight.models;

import java.util.List;

public class Requirements {

		
		private String reqId;
		private String userId;
		private String domain;
		private String jobRole;
		private String jobRoleType;
		private List<String> techStack;
		private double experience;

		public String getReqId() {
			return reqId;
		}

		public void setReqId(String reqId) {
			this.reqId = reqId;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getDomain() {
			return domain;
		}

		public void setDomain(String domain) {
			this.domain = domain;
		}

		public String getJobRole() {
			return jobRole;
		}

		public void setJobRole(String jobRole) {
			this.jobRole = jobRole;
		}

		public String getJobRoleType() {
			return jobRoleType;
		}

		public void setJobRoleType(String jobRoleType) {
			this.jobRoleType = jobRoleType;
		}

		public List<String> getTechStack() {
			return techStack;
		}

		public void setTechStack(List<String> techStack) {
			this.techStack = techStack;
		}

		public double getExperience() {
			return experience;
		}

		public void setExperience(double experience) {
			this.experience = experience;
		}
		

	}


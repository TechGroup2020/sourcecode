package com.perfumaa.rest.pojo;

import lombok.Data;

@Data
public class ResultObject {
	
	private Integer resultCode;
	
	private String resultMessage;
	
	private String sessionId;
	
	private String userName;
	
	private UserProfile userProfile;
	
	private String userRole;
	
	
}

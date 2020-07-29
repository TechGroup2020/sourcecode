package com.perfumaa.rest.pojo;

import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "users")
@Data
public class User {

	@Id
	private ObjectId userId;
	
	private String username;
	
	private String password;
	
	private String passwordToken;

	@Indexed(unique = true, direction = IndexDirection.DESCENDING)
	private String email;

	private boolean active;
	
	@DBRef
	private Set<UserRole> roles;

}
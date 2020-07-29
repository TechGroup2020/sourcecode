package com.perfumaa.rest.pojo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "userProfile")
public class UserProfile {   
	
	@Id
    private ObjectId id;
	private String userName;
	private String firstName;
	private String lastName;
	private String place;
    private String nickName;
    private String gender;    
    @Indexed(name="emailId", unique = true)
    private String emailId;
    private String dateOfBirth;
    private String phoneNumber;
	//private String address;
    
    public String getId() {
		return id.toHexString();
	}
	
}
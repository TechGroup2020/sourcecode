package com.perfumaa.rest.pojo;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "userRole")
public class UserRole {

	@Id
    private ObjectId roleId;
    
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String role;

    public String getRoleId() { return roleId.toHexString(); }
    public void setRoleId(ObjectId roleId) { this.roleId = roleId; }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}



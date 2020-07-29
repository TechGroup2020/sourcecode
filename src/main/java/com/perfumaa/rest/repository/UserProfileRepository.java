package com.perfumaa.rest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.perfumaa.rest.pojo.UserProfile;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile, String> {

	UserProfile findByEmailId(String emailId);

	
}
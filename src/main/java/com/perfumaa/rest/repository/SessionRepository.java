package com.perfumaa.rest.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.perfumaa.rest.pojo.Sessions;

@Repository
public interface SessionRepository extends MongoRepository<Sessions, String> {
	
	Optional<Sessions> findById(String sessionId);
	
	//Sessions findOne(String sessionId);

	//Optional<Sessions> findById(String sessionId);

}

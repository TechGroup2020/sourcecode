package com.perfumaa.rest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.perfumaa.rest.pojo.UserRole;

public interface RoleRepository extends MongoRepository<UserRole, String> {

	UserRole findByRole(String role);
}
package com.perfumaa.rest.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.perfumaa.rest.pojo.ResultObject;
import com.perfumaa.rest.pojo.User;
import com.perfumaa.rest.pojo.UserProfile;
import com.perfumaa.rest.pojo.UserRole;
import com.perfumaa.rest.repository.RoleRepository;
import com.perfumaa.rest.repository.UserProfileRepository;
import com.perfumaa.rest.repository.UserRepository;

@Service
public class CustomUserDetailsService{

	private static final Logger LOG = LogManager.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserProfileRepository profileRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	HttpServletRequest request;

	@Autowired
	MongoTemplate mongoTemplate; 


	/**
	 * This method will create the new user
	 * @param user
	 */
	public void createUser(User user){

		ResultObject ro = new ResultObject();


		Optional<User> userExists = userRepository.findByEmail(user.getEmail().toLowerCase());

		String email = user.getEmail().toLowerCase();
		user.setEmail(email);
		user.setUsername(user.getUsername());

		saveUser(user);

		ro.setResultMessage("User successfully Registered");
		LOG.info("User registered");
		UserProfile userProfile = new UserProfile();
		userProfile.setEmailId(user.getEmail().toLowerCase());
		userProfile.setNickName(user.getUsername());
		userProfile.setUserName(user.getUsername());
		profileRepository.save(userProfile);

	}

	private void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(true);
		UserRole userRole = roleRepository.findByRole("ROLE_USER");
		user.setRoles(new HashSet<>(Arrays.asList(userRole)));
		userRepository.save(user);
	}



}

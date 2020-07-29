package com.perfumaa.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.perfumaa.rest.pojo.ResultObject;
import com.perfumaa.rest.pojo.User;
import com.perfumaa.rest.repository.SessionRepository;
import com.perfumaa.rest.services.CustomUserDetailsService;

@RestController
public class CustomerController {

	private static final Logger LOG = LogManager.getLogger(CustomerController.class);

	@Autowired
	private CustomUserDetailsService userService;

	@Autowired
	SessionRepository sessionRepository;

	boolean isAuthenticated;

	@Autowired
	HttpServletRequest request;

	@RequestMapping(value = "/user/signup", method = RequestMethod.POST)
	public ResponseEntity<ResultObject> createNewUser(@RequestBody User user) {
		LOG.debug("inside signup" + user);
		userService.createUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
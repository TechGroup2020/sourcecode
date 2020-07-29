package com.perfumaa.rest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.perfumaa.rest.pojo.ResultObject;
import com.perfumaa.rest.pojo.UserRole;
import com.perfumaa.rest.repository.RoleRepository;


@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories
public class WebDemoSpringApplication implements WebMvcConfigurer {
	
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
			"classpath:/META-INF/resources/", "classpath:/resources/",
			"classpath:/static/", "classpath:/public/" };
	

	public static void main(String[] args) {
		SpringApplication.run(WebDemoSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {

	    return args -> {

	        UserRole adminRole = roleRepository.findByRole("ROLE_ADMIN");
	        if (adminRole == null) {
	        	UserRole newAdminRole = new UserRole();
	            newAdminRole.setRole("ROLE_ADMIN");
	            roleRepository.save(newAdminRole);
	        }

	        UserRole userRole = roleRepository.findByRole("ROLE_USER");
	        if (userRole == null) {
	        	UserRole newUserRole = new UserRole();
	            newUserRole.setRole("ROLE_USER");
	            roleRepository.save(newUserRole);
	        }
	    };

	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!registry.hasMappingForPattern("/webjars/**")) {
			registry.addResourceHandler("/webjars/**").addResourceLocations(
					"classpath:/META-INF/resources/webjars/");
		}
		if (!registry.hasMappingForPattern("/**")) {
			registry.addResourceHandler("/**").addResourceLocations(
					CLASSPATH_RESOURCE_LOCATIONS);
		}
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
		
	@Bean
	public ResultObject resultObject() {
		ResultObject resultObject = new ResultObject();
		return resultObject;
	}
	
	
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   return builder.build();
	}

	
	
}

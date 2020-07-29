package com.perfumaa.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
class EmbeddedMongoPortLogger implements ApplicationRunner, EnvironmentAware {

	private static final Logger logger = LoggerFactory.getLogger(EmbeddedMongoPortLogger.class);

	private Environment environment;

	public void run(ApplicationArguments args) throws Exception {
		String port = this.environment.getProperty("local.mongo.port");
		logger.info("Embedded Mongo started on port " + port +
				", use 'mongo --port " + port + "' command to connect");
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

}
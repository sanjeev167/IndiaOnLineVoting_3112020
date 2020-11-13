package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import com.pon.pub.hm.repo.OnlineVoterAccountRepository;

/**
 * To make your Spring Boot Web App work as a deployable WAR file we will need
 * to:
 * 
 * Open Java class that contains the public static void main(String[] args) and
 * make it extend the SpringBootServletInitializer class Call the
 * application.sources(DeployableWarApplication.class) method as in the example
 * below. Where the DeployableWarApplication.class is the class which contains
 * the public static void main(String[] args) method.
 * 
 * Add <packaging>war</packaging> in pom.xml
 * 
 * Please note that the <scope> XML element of spring-boot-starter-tomcat contains the value provided.
 * maven clean 
 * maven install
 */
@SpringBootApplication
public class IndiaOnLineVotingApplication extends SpringBootServletInitializer implements CommandLineRunner{

	@Autowired
	private ApplicationContext context;
	
	public static void main(String[] args) {
		SpringApplication.run(IndiaOnLineVotingApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(IndiaOnLineVotingApplication.class);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}
}

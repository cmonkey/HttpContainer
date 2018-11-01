package com.service.httpContainer;

import com.service.httpContainer.container.HttpEmbeddedServletContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
//@SpringBootApplication
public class HttpContainerApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(HttpEmbeddedServletContainer.class);
		springApplication.run(args);
	}
}

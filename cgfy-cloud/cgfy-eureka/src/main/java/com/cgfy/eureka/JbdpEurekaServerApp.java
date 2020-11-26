package com.cgfy.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class JbdpEurekaServerApp {

	public static void main(String[] args) {
		SpringApplication.run(JbdpEurekaServerApp.class, args);
	}

}

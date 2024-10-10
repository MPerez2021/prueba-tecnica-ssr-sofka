package com.microservice.group_two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceGroupTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceGroupTwoApplication.class, args);
	}

}

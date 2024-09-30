package com.example.User_microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;
@ComponentScan("com")
@EntityScan("com")
@EnableJpaRepositories("com")
@SpringBootApplication
@EnableDiscoveryClient
public class UserMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMicroservicesApplication.class, args);
	}
	
	
	@Bean("webclient")
	@LoadBalanced
	public WebClient.Builder getWebClient(){
		return WebClient.builder();
	}

}

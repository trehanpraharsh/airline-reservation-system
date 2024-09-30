package com.airlinereservationsystem.admin_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ComponentScan("com")
@EntityScan("com")
@EnableJpaRepositories("com")
@EnableDiscoveryClient
public class AdminMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminMsApplication.class, args);
	}

	
	@Bean("webclient")
	@LoadBalanced
	public WebClient.Builder getWebClient(){
		return WebClient.builder();
	}
}

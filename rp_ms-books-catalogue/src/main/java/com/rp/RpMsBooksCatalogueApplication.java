package com.rp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RpMsBooksCatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpMsBooksCatalogueApplication.class, args);
	}

}

package com.crp.ucp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CrpApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrpApplication.class, args);
	}

}

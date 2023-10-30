package com.ensaa.constructionapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan("com.ensaa.constructionapp.model")
@EnableJpaRepositories("com.ensaa.constructionapp.repository")
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImpl")
@EnableScheduling
public class ConstructionAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConstructionAppApplication.class, args);
	}

}

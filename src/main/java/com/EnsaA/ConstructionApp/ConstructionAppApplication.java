package com.EnsaA.ConstructionApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan("com.EnsaA.ConstructionApp.model")
@EnableJpaRepositories("com.EnsaA.ConstructionApp.repository")
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImpl")
@EnableScheduling
public class ConstructionAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConstructionAppApplication.class, args);
	}

}

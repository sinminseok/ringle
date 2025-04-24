package com.minseok.ringle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RingleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RingleApplication.class, args);
	}

}

package com.example.gagong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GagongApplication {

	public static void main(String[] args) {
		SpringApplication.run(GagongApplication.class, args);
	}

}

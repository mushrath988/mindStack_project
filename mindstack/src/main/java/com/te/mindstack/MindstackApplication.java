package com.te.mindstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MindstackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MindstackApplication.class, args);
	}

}

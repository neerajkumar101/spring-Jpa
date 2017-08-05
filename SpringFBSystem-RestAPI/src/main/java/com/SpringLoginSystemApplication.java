package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//(exclude = {SecurityAutoConfiguration.class })
public class SpringLoginSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLoginSystemApplication.class, args);
	}
}

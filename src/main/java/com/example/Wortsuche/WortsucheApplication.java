package com.example.Wortsuche;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class WortsucheApplication {

	public static void main(String[] args) {
		SpringApplication.run(WortsucheApplication.class, args);
	}

}

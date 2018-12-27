package com.heyjie.springboot1;

import com.heyjie.springboot1.config.HeyjieConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Springboot1Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot1Application.class, args);
	}

	@Bean
	public HeyjieConfig rpConfig()
	{
		return new HeyjieConfig();
	}
}


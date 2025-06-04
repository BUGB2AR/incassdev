package com.jarmison.incassdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jarmison.incassdev*"})
public class IncassdevApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncassdevApplication.class, args);
	}

}

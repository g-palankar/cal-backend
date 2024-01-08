package com.cal.calbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class CalBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalBackendApplication.class, args);
	}

}

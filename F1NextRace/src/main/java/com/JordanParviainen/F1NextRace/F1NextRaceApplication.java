package com.JordanParviainen.F1NextRace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class F1NextRaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(F1NextRaceApplication.class, args);
	}

}

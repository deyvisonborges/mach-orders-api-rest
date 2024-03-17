package com.mach.machorderrestapi;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class MachOrderRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MachOrderRestApiApplication.class, args);
	}

}

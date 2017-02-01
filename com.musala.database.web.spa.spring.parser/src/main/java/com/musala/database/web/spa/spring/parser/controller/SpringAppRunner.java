package com.musala.database.web.spa.spring.parser.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.musala.database.web.spa.spring.parser.model.impl")
@ComponentScan("com.musala.database.web.spa.spring.parser.requests")
public class SpringAppRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringAppRunner.class, args);
	}
}
package com.tempest.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableResourceServer
public class Application {

	class Message {
		private String message;

		public Message(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}
	}

	@RequestMapping(path = "/echo/{message}", method= RequestMethod.GET)
	public Message echo(@PathVariable("message") String message) {
		return new Message(message);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
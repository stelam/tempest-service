package com.tempest.service;

import com.tempest.service.model.Member;
import com.tempest.service.model.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.MemberService;

import java.util.Arrays;

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

	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner init(MemberService memberService) {
		try {
			memberService.findMemberByUsername("admin");
			return null;
		} catch (UsernameNotFoundException e) {
			return (evt) -> Arrays.asList(
				"user,admin,john,robert,ana".split(",")).forEach(
				username -> {
					Member member = new Member();
					member.setUsername(username);
					member.setPassword("password");
					member.grantAuthority(Role.ROLE_USER);
					if (username.equals("admin"))
						member.grantAuthority(Role.ROLE_ADMIN);
					memberService.registerMember(member);
				}
			);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
package org.test.usersservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.test.usersservice.entities.User;
import org.test.usersservice.services.UserService;

@SpringBootApplication
public class UsersServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UsersServiceApplication.class, args);
	}
	@Autowired
	UserService userService;
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello, Spring Boot!");
		User user = new User();
		user.setUsername("john_doe");
		user.setEmail("aza@gzlk.com");
		userService.createUser(user);
		System.out.println(user);

	}
}

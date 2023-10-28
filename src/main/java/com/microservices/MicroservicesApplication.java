package com.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class MicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesApplication.class, args);
	}

}


//@Controller
class SimpleController {


	@GetMapping("/")
	public String home()
	{
		return "home.html";
	}

	@GetMapping("/landing")
	public String landing()
	{
		return "landing.html";
	}

	@GetMapping("/user")
	public String user()
	{
		return "user.html";
	}



}


//@Controller
class LoginController {

	@GetMapping("/login")
	public String login()
	{
		return "login.html";
	}

	@GetMapping("/register")
	public String register()
	{
		return "register.html";
	}



}

//@RestController
 class AuthController{

 	@PostMapping(path = "/auth",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<?> authenticate(@ModelAttribute Credentials request){
		return ResponseEntity.ok().body(request);
	}
 
}

class Credentials {
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String username;
	private String password;
}
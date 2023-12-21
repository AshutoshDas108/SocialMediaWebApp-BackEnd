package com.socials.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping
	public String homeControllerHandler() {
		return "This is Home Controller";
	}

	
	@GetMapping("/home")
	public String homeControllerHandler2() {
		return "This is Home Controller2";
	}

}

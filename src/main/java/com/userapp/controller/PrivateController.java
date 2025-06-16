package com.userapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/private")
@RestController
public class PrivateController {

	@GetMapping("/info")
	public String getPrivateInfo() {
		return "Amit";
	}
}

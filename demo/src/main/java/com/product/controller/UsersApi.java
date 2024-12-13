package com.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.product.userEntity.UserEntity;

public interface UsersApi {

	@PostMapping("/api/register")
	ResponseEntity<Object> registerUser(@RequestBody UserEntity product);

	@GetMapping("/api/login")
	ResponseEntity<Object> loginUser(@RequestBody String userName, String password);
}

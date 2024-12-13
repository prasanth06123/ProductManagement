package com.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.product.userEntity.UserEntity;
import com.product.userService.UserServiceImpl;

public class UsersApiController implements UsersApi {

	@Override
	public ResponseEntity<Object> registerUser(UserEntity user) {

		try {

			Map<String, Object> response = new HashMap<>();
			UserServiceImpl userServiceImpl = new UserServiceImpl();
			response = userServiceImpl.registerUser(user);
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public ResponseEntity<Object> loginUser(String userName, String password) {

		try {

			Map<String, Object> response = new HashMap<>();
			UserServiceImpl userServiceImpl = new UserServiceImpl();
			response = userServiceImpl.loginUser(userName, password);
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {

		}
		return null;
	}

}

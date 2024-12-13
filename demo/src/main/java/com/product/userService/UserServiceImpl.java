package com.product.userService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.product.authentication.JWTAuthenticationService;
import com.product.db.DBUtils;
import com.product.userEntity.UserEntity;

public class UserServiceImpl {

	private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);

	DBUtils dbUtils = new DBUtils();

	public Map<String, Object> registerUser(UserEntity user) {

		Map<String, Object> response = new HashMap<>();

		try {

			if (user != null) {

				validateUserData(user, response);
				if (!response.isEmpty()) {
					return response;
				}

				checkUserAlreadyExist(user, response);
				if (!response.isEmpty()) {
					return response;
				}

				int rowsAffected = dbUtils.addUser(user);

				if (rowsAffected > 0) {
					response.put("status", "SUCCESS");
					response.put("message", "User register successfully");
				} else {
					response.put("status", "ERROR");
					response.put("message", "Oops! something went wrong.Try again later");
				}
			}

		} catch (Exception e) {
			LOG.error("ERROR @registerUser =>" + e.getMessage(), e);
		}
		return response;
	}

	public Map<String, Object> loginUser(String userName, String password) {

		Map<String, Object> response = new HashMap<>();
		String token = "";

		try {

			if (!userName.isEmpty() && !password.isEmpty()) {

				checkValidUser(userName, password, response);
				if (!response.isEmpty()) {
					return response;
				}

				JWTAuthenticationService jwtAuthenticationService = new JWTAuthenticationService();
				token = jwtAuthenticationService.generateToken(userName);

				if (!token.isEmpty()) {
					response.put("status", "SUCCESS");
					response.put("message", "User loggedIn successfully");
					response.put("token", token);
				} else {
					response.put("status", "ERROR");
					response.put("message", "Oops! something went wrong.Try again later");
				}

			}

		} catch (Exception e) {
			LOG.error("ERROR @loginUser =>" + e.getMessage(), e);
		}
		return response;
	}

	private Map<String, Object> validateUserData(UserEntity user, Map<String, Object> response) {

		try {

			String name = user.getName();
			String gender = user.getGender();
			String mobileNumber = user.getMobileNumber();
			String emailAddress = user.getEmailAddress();

			if (name.isEmpty() || gender.isEmpty() || mobileNumber.isEmpty() || emailAddress.isEmpty()) {

				response.put("status", "ERROR");
				response.put("message", "Invalid Details");
			}

		} catch (Exception e) {
			LOG.error("ERROR @validateUserData =>" + e.getMessage(), e);
		}
		return response;
	}

	private Map<String, Object> checkUserAlreadyExist(UserEntity user, Map<String, Object> response) {

		try {

			List<Map<String, Object>> userData = dbUtils.retrieveUser(user, null);

			if (!userData.isEmpty()) {
				response.put("status", "ERROR");
				response.put("message", "User Already exist");
			}

		} catch (Exception e) {
			LOG.error("ERROR @checkUserAlreadyExist =>" + e.getMessage(), e);
		}

		return response;
	}

	private Map<String, Object> checkValidUser(String userName, String password, Map<String, Object> response) {

		try {

			UserEntity user = new UserEntity();
			user.setEmailAddress(userName);
			user.setPassword(password);

			List<Map<String, Object>> userData = dbUtils.retrieveUser(user, "login");

			if (!userData.isEmpty()) {
				response.put("status", "ERROR");
				response.put("message", "Invalid User");
			}

		} catch (Exception e) {
			LOG.error("ERROR @checkUserAlreadyExist =>" + e.getMessage(), e);
		}

		return response;
	}

}

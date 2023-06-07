package com.te.mindstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.mindstack.dto.UserDto;
import com.te.mindstack.dto.UserUpdateDto;
import com.te.mindstack.response.AppResponse;
import com.te.mindstack.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<AppResponse> userRegister(@RequestBody UserDto userDto) {
		UserDto existingUser = userService.register(userDto);
		if (existingUser != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new AppResponse(false, "register successful", 200, existingUser));

		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AppResponse(true, "register not successful", 400, null));
		}
	}

	@PutMapping("/updateUserDetails/{userId}")
	public ResponseEntity<AppResponse> updateUserDetails(@PathVariable Integer userId, @RequestBody UserUpdateDto userUpdateDto) {
		UserUpdateDto updatedUser = userService.updateUser(userId, userUpdateDto);
		if (updatedUser != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new AppResponse(false, "updated successful", 200, updatedUser));

		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AppResponse(true, "something went wrong", 400, null));
		}
	}
}

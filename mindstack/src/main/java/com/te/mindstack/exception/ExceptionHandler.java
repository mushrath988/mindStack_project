package com.te.mindstack.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.mindstack.response.AppResponse;

@RestControllerAdvice
public class ExceptionHandler {

	@Autowired
	private AppResponse appResponse;

	@org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<AppResponse> ResourceNotFoundException(ResourceNotFoundException ex) {
		appResponse.setError(false);
		appResponse.setMessage(ex.getMessage());
		appResponse.setStatus(400);
		return new ResponseEntity<>(appResponse, HttpStatus.OK);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<AppResponse> UserNotFoundException(UserNotFoundException ex) {
		appResponse.setError(false);
		appResponse.setMessage(ex.getMessage());
		appResponse.setStatus(400);
		return new ResponseEntity<>(appResponse, HttpStatus.OK);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<AppResponse> RoleNotFoundException(RoleNotFoundException ex) {
		appResponse.setError(false);
		appResponse.setMessage(ex.getMessage());
		appResponse.setStatus(400);
		return new ResponseEntity<>(appResponse, HttpStatus.OK);
	}
}

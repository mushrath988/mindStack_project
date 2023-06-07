package com.te.mindstack.response;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
public class AppResponse {
	private boolean error;
	private String message;
	private int status;
	private Object data;

}

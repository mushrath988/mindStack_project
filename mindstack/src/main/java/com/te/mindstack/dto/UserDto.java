package com.te.mindstack.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {
	private int userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	private long userMobileNumber;
	private int userAge;
	private String userGender;
	private String userDOB;
	private List<Integer> roleIds;

}

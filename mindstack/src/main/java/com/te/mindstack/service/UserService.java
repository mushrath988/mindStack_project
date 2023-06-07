package com.te.mindstack.service;

import com.te.mindstack.dto.UserDto;
import com.te.mindstack.dto.UserUpdateDto;

public interface UserService {

	UserDto register(UserDto userDto);

	UserUpdateDto updateUser(Integer userId, UserUpdateDto userUpdateDto);

}

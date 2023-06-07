package com.te.mindstack.serviceimpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.te.mindstack.dto.UserDto;
import com.te.mindstack.dto.UserUpdateDto;
import com.te.mindstack.entity.Role;
import com.te.mindstack.entity.User;
import com.te.mindstack.exception.UserNotFoundException;
import com.te.mindstack.repository.RoleRepository;
import com.te.mindstack.repository.UserRepository;
import com.te.mindstack.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto register(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		user.setUserPassword(this.passwordEncoder.encode(userDto.getUserPassword()));
		if (roleRepository.findAll().isEmpty()) {
			Role role = new Role();
			role.setRoleName("Admin");
			role.setUser(Arrays.asList(user));
			List<Role> roles = Arrays.asList(role);
			user.setRoles(new HashSet<>(roles));
		} else {
			List<Role> roles = roleRepository.findAllById(userDto.getRoleIds());
			user.setRoles(new HashSet<>(roles));
			roles.forEach(role -> {
				role.getUser().add(user);
			});
		}
		userRepository.save(user);
		return userDto;
	}

	@Override
	public UserUpdateDto updateUser(Integer userId, UserUpdateDto userUpdateDto) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			User users = user.get();
			BeanUtils.copyProperties(userUpdateDto, users);
			userRepository.save(users);
			return userUpdateDto;
		} else {
			throw new UserNotFoundException("user is not found");
		}
	}

}

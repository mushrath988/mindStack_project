package com.te.mindstack.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.te.mindstack.entity.User;
import com.te.mindstack.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         //fetching user from database

		Optional<User> user = userRepository.findByUserEmail(username);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("could not found user");
		}
		CustomUserDetails customUserDetails = new CustomUserDetails(user.get());
		return customUserDetails;
	}

}

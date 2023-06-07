package com.te.mindstack.serviceimpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.mindstack.dto.RoleDto;
import com.te.mindstack.entity.Role;
import com.te.mindstack.exception.RoleNotFoundException;
import com.te.mindstack.repository.RoleRepository;
import com.te.mindstack.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public RoleDto createRole(RoleDto roleDto) {
		Role role = new Role();
		BeanUtils.copyProperties(roleDto, role);
		roleRepository.save(role);
		BeanUtils.copyProperties(role, roleDto);
		return roleDto;
	}

	@Override
	public boolean removeRole(Integer roleId) {
		Optional<Role> role = roleRepository.findById(roleId);
		if (role.isPresent()) {
			Role existRole = role.get();
			roleRepository.delete(existRole);
			return true;
		}
		throw new RoleNotFoundException("role is not found");
	}

}

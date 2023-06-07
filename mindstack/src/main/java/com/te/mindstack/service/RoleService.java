package com.te.mindstack.service;

import com.te.mindstack.dto.RoleDto;

public interface RoleService {

	RoleDto createRole(RoleDto roleDto);

	boolean removeRole(Integer roleId);

}

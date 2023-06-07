package com.te.mindstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.mindstack.dto.RoleDto;
import com.te.mindstack.response.AppResponse;
import com.te.mindstack.service.RoleService;

@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping("/addRole")
	public ResponseEntity<AppResponse> addRole(@RequestBody RoleDto roleDto) {
		RoleDto roleDtos = roleService.createRole(roleDto);
		if (roleDtos != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new AppResponse(false, "role is added", 200, roleDtos));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new AppResponse(true, "something went wrong", 400, null));
		}
	}
	@DeleteMapping("/roles/{roleId}")
    public ResponseEntity<AppResponse> deleteRole(@PathVariable Integer roleId) {
        boolean deletedRole = roleService.removeRole(roleId);
        if (deletedRole) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new AppResponse(false, "Role deleted successfully", 200, deletedRole));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new AppResponse(true, "Role not found", 404, null));
        }
    }
}

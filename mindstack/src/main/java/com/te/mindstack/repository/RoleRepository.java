package com.te.mindstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.mindstack.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}

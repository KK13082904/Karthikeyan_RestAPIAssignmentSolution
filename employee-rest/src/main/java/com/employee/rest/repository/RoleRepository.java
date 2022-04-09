package com.employee.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.rest.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}

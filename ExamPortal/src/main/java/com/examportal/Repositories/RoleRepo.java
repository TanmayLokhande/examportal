package com.examportal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}

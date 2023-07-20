package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergisticit.domain.Role;

//no need to annotate tags because it extends JpaRepository
public interface RoleRepository extends JpaRepository<Role, Long> {  // <class name, type of a primary key>

}

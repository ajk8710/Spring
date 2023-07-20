package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergisticit.domain.User;

//no need to annotate tags because it extends JpaRepository
public interface UserRepository extends JpaRepository<User, Long> {  // <class name, type of a primary key>

}

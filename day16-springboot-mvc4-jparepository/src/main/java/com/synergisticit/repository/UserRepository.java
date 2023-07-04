package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergisticit.domain.User;

// no need to annotate because it extends JpaRepository
public interface UserRepository extends JpaRepository<User, Long> {  // class name, object type of property that is a primary key
    User findByUsername(String username);  // username should match the property name
}

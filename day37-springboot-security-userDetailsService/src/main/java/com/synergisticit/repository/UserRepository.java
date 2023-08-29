package com.synergisticit.repository;

import org.springframework.data.repository.CrudRepository;

import com.synergisticit.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);  // using nomenclature
}

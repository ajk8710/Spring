package com.synergisticit.repository;

import org.springframework.data.repository.CrudRepository;

import com.synergisticit.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}

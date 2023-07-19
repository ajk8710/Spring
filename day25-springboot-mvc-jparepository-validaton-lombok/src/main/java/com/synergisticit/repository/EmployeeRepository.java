package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergisticit.domain.Employee;

//no need to annotate tags because it extends JpaRepository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {// <class name, type of a primary key>

}

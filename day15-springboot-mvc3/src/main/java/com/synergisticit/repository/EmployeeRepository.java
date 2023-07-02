package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergisticit.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {  // class name, object type of property that is a primary key

}

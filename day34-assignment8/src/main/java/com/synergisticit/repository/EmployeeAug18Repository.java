package com.synergisticit.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.synergisticit.domain.EmployeeAug18;

public interface EmployeeAug18Repository extends JpaRepository<EmployeeAug18, Integer> {
    
    // Find the employees sorted on last name
    List<EmployeeAug18> findAll(Sort sort);  // using nomenclature
    
    // Find the employees sorted on ascending order of their firstName
    List<EmployeeAug18> findByOrderByFirstNameAsc();  // using nomenclature
    
    // Find employee with maximum empId
    @Query("select e from EmployeeAug18 e where e.empId=(select max(empId) from EmployeeAug18)")  // using Hibernate Query Language / JPA Query Language
    EmployeeAug18 findEmployeeWithMaxId();
    
    // Find employees whose salary is between a given range
    @Query(value = "select * from EmployeeAug18 where salary >= :minSalary and salary <= :maxSalary", nativeQuery = true)  // using native sql query
    List<EmployeeAug18> findEmpsWithSalaryBetween(@Param("minSalary") double minSalary, @Param("maxSalary")  double maxSalary);
    
}

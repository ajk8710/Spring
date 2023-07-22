package com.synergisticit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.synergisticit.domain.Employee;

import jakarta.transaction.Transactional;

//no need to annotate tags because it extends JpaRepository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {// <class name, type of a primary key>
    
    // query data by method name using JpaRepository (must follow nomenclature. field name must match.)
    List<Employee> findByName(String name);
    List<Employee> findByDesignation(String designation);
    List<Employee> getByDesignation(String designation);
    List<Employee> findBySalaryGreaterThan(int salary);
    List<Employee> findBySalaryLessThan(int salary);
    List<Employee> findBySalaryBetween(int sal1, int sal2);
    
    // Use @Query for method names that do not follow nomenclature (Hibernate Query Language or JPA Query Language)
    @Query("select e from Employee e where e.name=:name")  // Employee should be a class name. Not table name like employee.
    List<Employee> findByName2(@Param(value="name") String name);
    
    @Query("select e from Employee e where e.name like %:name%")  // HQL, Hibernate Query Language
    List<Employee> findByNameLike(@Param(value="name")String name);
    
    @Query("select e from Employee e where e.name like %?1%")  // HQL, Hibernate Query Language
    List<Employee> findByNameLike2(String name);
    
    @Query(value="select * from employee where name like %?1%", nativeQuery=true)  // SQL
    List<Employee> findByNameLikeNativeQuery(String name);
    
    @Transactional  // treat as value returning query like select, even though it's update
    @Modifying  // treat as value returning query like select, even though it's update
    @Query("update Employee e set e.salary = e.salary + e.salary * :byPercent / 100 where e.salary < :minSal")
    int updateSalaries(@Param("byPercent") int byPercent, @Param("minSal") int minSal);
    
    @Transactional
    @Modifying
    @Query("delete from Employee e where e.empId=:empId")
    void deleteByEmpId(@Param("empId") int empId);
    
    @Query(value="select * from employee where empId=?", nativeQuery=true)  // SQL
    Employee getById(@Param("empId") int empId);
    
    // May return more than one employee - better to change return type List<Employee>
    @Query("select e from Employee e where e.name=:name and e.designation=:designation")  // HQL
    Employee findByNameAndDesignation(@Param("name") String name, @Param("designation") String designation);
    
    // @Query not necessary. It follows nomenclature.
    // @Query("select e from Employee e where e.designation=:designation and e.salary=:salary")  // HQL
    List<Employee> findByDesignationAndSalary(@Param("designation") String designation, @Param("salary") int salary);
    
//    @Query("select e from Employee e where e.name=:name and e.designation=:designation and e.salary=:salary")  // HQL
//    Employee findByNameAndDesignationAndSalary
//    (@Param("name") String name, @Param("designation") String designation, @Param("salary") int salary);
    
    @Transactional
    @Modifying
    @Query(value="insert into employee (empId, name, designation, salary, insured)"
                            + "values (:empId, :name, :designation, :salary, :insured)", nativeQuery=true)  // SQL
    void insertEmployee(@Param("empId") int empId, @Param("name") String name, @Param("designation") String designation, 
            @Param("salary") int salary, @Param("insured") boolean insured);
    
    @Query(value="select name from employee order by name", nativeQuery=true)  // SQL
    List<String> findAllOrderByName();
}

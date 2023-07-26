package com.synergisticit.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.synergisticit.domain.Employee;

public interface EmployeeService {
    public Employee save(Employee employee);
    public List<Employee> findAll();
    public Employee findById(int empId);
    public void deleteById(int empId);
    public boolean existsByEmpId(int empId);
    
    // custom methods
    public List<Employee> findByName(String name);
    public List<Employee> findByDesignation(String designation);
    public List<Employee> getByDesignation(String designation);
    public List<Employee> findBySalaryGreaterThan(int salary);
    public List<Employee> findBySalaryLessThan(int salary);
    public List<Employee> findBySalaryBetween(int sal1, int sal2);
    public List<Employee> findByName2(String name);
    public List<Employee> findByNameLike(String name);
    public List<Employee> findByNameLike2(String name);
    public List<Employee> findByNameLikeNativeQuery(String name);
    public int updateSalaries(int byPercent, int minSal);
    public void deleteByEmpId(int empId);
    public Employee getById(int empId);
    public Employee findByNameAndDesignation(String name, String designation);
    public List<Employee> findByDesignationAndSalary(String designation, int salary);
    public void insertEmployee(int empId, String name, String designation, int salary, boolean insured);
    public List<String> findAllOrderByName();
    public List<Employee> findAll(Sort sort);
    public List<Employee> findByOrderBySalaryAsc();
    public List<Employee> findByOrderBySalaryDesc();
    public List<Employee> findBySalaryRange(int minSalary, int maxSalary);
    public Page<Employee> findAll(Pageable pageable);
    
}

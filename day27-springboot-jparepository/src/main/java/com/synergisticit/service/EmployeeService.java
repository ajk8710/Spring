package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Employee;

public interface EmployeeService {
    public Employee save(Employee employee);
    public List<Employee> findAll();
    public Employee findById(int empId);
    public void deleteById(int empId);
    
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
    int updateSalaries(int byPercent, int minSal);
}

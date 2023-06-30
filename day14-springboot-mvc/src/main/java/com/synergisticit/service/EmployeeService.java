package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Employee;

public interface EmployeeService {
    
    // CRUD
    public void save(Employee e);
    public List<Employee> findAll();
    public Employee findById(int empId);
    public Employee updateById(int empId, String designation, int salary);
    public int deleteById(int empId);

}

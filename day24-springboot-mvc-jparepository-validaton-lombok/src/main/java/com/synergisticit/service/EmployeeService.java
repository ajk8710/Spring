package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Employee;

public interface EmployeeService {
    public Employee save(Employee employee);
    public List<Employee> findAll();
    public Employee findById(int empId);
    public void deleteById(int empId);
}

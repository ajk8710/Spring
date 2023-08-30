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
    public boolean existsById(int empId);
    
}

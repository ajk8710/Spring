package com.synergisticit.dao;

import java.util.List;

import com.synergisticit.domain.Employee;

public interface EmployeeDao {
    
    public int save(Employee e);
    public List<Employee> findAll();
    public Employee findById(int empId);
    public Employee updateById(int empId, String designation, int salary);
    public int deleteById(int empId);
    
}

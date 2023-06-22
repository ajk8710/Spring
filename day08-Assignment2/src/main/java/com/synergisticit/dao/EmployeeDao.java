package com.synergisticit.dao;

import java.util.List;

import com.synergisticit.domain.Employee;

public interface EmployeeDao {
    public abstract int createEmployee(Employee e);
    public abstract Employee getEmployee(int empId);
    public abstract List<Employee> getAllEmployees();
    public abstract int updateEmployee(int empId, String designation, int salary);
    public abstract int deleteEmployee(int empId);
}

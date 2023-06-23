package com.synergisticit.dao;

import java.util.List;

import com.synergisticit.domain.Employee;

public interface EmployeeDao2 {
        
//    public void save(Employee e);
//    public void save2(Employee e);
//    public List<Employee> findAll();
//    public Employee findById(int empId);
//    public Employee updateById(int empId, String designation, int salary);
//    public int deleteById(int empId);
    
    public abstract int createEmployee(Employee e);
    public abstract Employee getEmployeeByEmpId(int empId);
    public abstract Employee getEmployeeByEmpId2(int empId);
    public abstract String getEmployeeByEmpId3(int empId);
    public abstract List<Employee> findAllEmployees();
    public abstract Employee updateEmployeeByEmpId(int empId, String designation, int salary);
    public abstract int deleteEmployeeByEmpId(int empId);

}

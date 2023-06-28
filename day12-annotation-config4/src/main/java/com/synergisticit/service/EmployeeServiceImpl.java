package com.synergisticit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.synergisticit.dao.EmployeeDao;
import com.synergisticit.dao.EmployeeDao2;
import com.synergisticit.domain.Employee;

public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;  // no need for setter when @Autowired
    
    @Autowired
    EmployeeDao2 employeeDao2;  // no need for setter when @Autowired
    
    @Override
    public void save(Employee e) {
        employeeDao.save(e);
    }

    @Override
    public void save2(Employee e) {
        employeeDao2.createEmployee(e);
        
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Employee findById(int empId) {
        return employeeDao.findById(empId);
    }

    @Override
    public Employee updateById(int empId, String designation, int salary) {
        return employeeDao.updateById(empId, designation, salary);
    }

    @Override
    public int deleteById(int empId) {
        employeeDao.deleteById(empId);
        return 0;
    }

}
 
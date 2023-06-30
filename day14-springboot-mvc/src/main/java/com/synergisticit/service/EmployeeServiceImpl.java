package com.synergisticit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.synergisticit.dao.EmployeeDao;
import com.synergisticit.domain.Employee;

@Service(value = "EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired EmployeeDao employeeDao;

    @Override
    public void save(Employee e) {
        System.out.println();
        employeeDao.save(e);
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> listOfEmployees = employeeDao.findAll();
        return listOfEmployees;
    }

    @Override
    public Employee findById(int empId) {
        Employee e = employeeDao.findById(empId);
        return e;
    }

    @Override
    public Employee updateById(int empId, String designation, int salary) {
        Employee e = employeeDao.updateById(empId, designation, salary);
        return e;
    }

    @Override
    public int deleteById(int empId) {
        int rv = employeeDao.deleteById(empId);
        return rv;
    }

}

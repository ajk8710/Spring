package com.synergisticit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.synergisticit.dao.EmployeeDao;
import com.synergisticit.domain.Employee;

@Service(value = "employeeService3")
public class EmployeeServiceImpl3 implements EmployeeService {
    
    @Qualifier(value="employeeDao3")  // can specify which to use when beans of same type presents
    @Autowired EmployeeDao employeeDao;

    @Override
    public void save(Employee e) {
        System.out.println();
        employeeDao.save(e);

    }

    @Override
    public void save2(Employee e) {
        // TODO Auto-generated method stub

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

package com.synergisticit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Employee;
import com.synergisticit.repository.EmployeeRepository;

@Service(value = "employeeServiceRepo")
public class EmployeeServiceImplRepo implements EmployeeService {
    
    @Autowired EmployeeRepository employeeRepository;

    @Override
    public void save(Employee e) {
        System.out.println("EmployeeServiceRepo.save() calling employeeRepository.save()");
        employeeRepository.save(e);
    }

    @Override
    public List<Employee> findAll() {
        // TODO Auto-generated method stub
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int empId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Employee updateById(int empId, String designation, int salary) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int deleteById(int empId) {
        // TODO Auto-generated method stub
        return 0;
    }

}

package com.synergisticit.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Employee;
import com.synergisticit.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired EmployeeRepository employeeRepository;
    
    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        // int i1 = 100;
        // int i2 = i1/0;  // throws exception
        
        List<Employee> employees = new ArrayList<>();
        Iterable<Employee> iterable =  employeeRepository.findAll();
        Iterator<Employee> itr = iterable.iterator();
        while (itr.hasNext()) {
            employees.add(itr.next());
        }
        employees.forEach(System.out::println);
        return employees;
    }

    @Override
    public Employee findById(int empId) {
        Optional<Employee> optEmployee = employeeRepository.findById(empId);  // findById on JpaRepository returns Optional<User> or Optional with default EMPTY value if not exists
        
        if (optEmployee.isPresent()) {
            return optEmployee.get();
        }
        return null;
    }

    @Override
    public void deleteById(int empId) {
        employeeRepository.deleteById(empId);
    }
    
    @Override
    public boolean existsByEmpId(int empId) {
        return employeeRepository.existsById(empId);
    }

}

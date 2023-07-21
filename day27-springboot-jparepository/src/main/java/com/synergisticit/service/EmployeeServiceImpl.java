package com.synergisticit.service;

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
        return employeeRepository.findAll();
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
    public List<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Override
    public List<Employee> findByDesignation(String designation) {
        return employeeRepository.findByDesignation(designation);
    }

    @Override
    public List<Employee> getByDesignation(String designation) {
        return employeeRepository.getByDesignation(designation);
    }

    @Override
    public List<Employee> findBySalaryGreaterThan(int salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }

    @Override
    public List<Employee> findBySalaryLessThan(int salary) {
        return employeeRepository.findBySalaryLessThan(salary);
    }

    @Override
    public List<Employee> findBySalaryBetween(int sal1, int sal2) {
        return employeeRepository.findBySalaryBetween(sal1, sal2);
    }

    @Override
    public List<Employee> findByName2(String name) {
        return employeeRepository.findByName2(name);
    }

    @Override
    public List<Employee> findByNameLike(String name) {
        return employeeRepository.findByNameLike(name);
    }

    @Override
    public List<Employee> findByNameLike2(String name) {
        return employeeRepository.findByNameLike2(name);
    }

    @Override
    public List<Employee> findByNameLikeNativeQuery(String name) {
        return employeeRepository.findByNameLikeNativeQuery(name);
    }

    @Override
    public int updateSalaries(int byPercent, int minSal) {
        return employeeRepository.updateSalaries(byPercent, minSal);
    }

}

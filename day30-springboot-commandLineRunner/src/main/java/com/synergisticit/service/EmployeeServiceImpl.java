package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public boolean existsByEmpId(int empId) {
        return employeeRepository.existsById(empId);
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

    @Override
    public void deleteByEmpId(int empId) {
        employeeRepository.deleteByEmpId(empId);
    }

    @Override
    public Employee getById(int empId) {
        return employeeRepository.getById(empId);
    }

    @Override
    public Employee findByNameAndDesignation(String name, String designation) {
        return employeeRepository.findByNameAndDesignation(name, designation);
    }

    @Override
    public List<Employee> findByDesignationAndSalary(String designation, int salary) {
        return employeeRepository.findByDesignationAndSalary(designation, salary);
    }

    @Override
    public void insertEmployee(int empId, String name, String designation, int salary, boolean insured) {
        employeeRepository.insertEmployee(empId, name, designation, salary, insured);
    }

    @Override
    public List<String> findAllOrderByName() {
        return employeeRepository.findAllOrderByName();
    }

    @Override
    public List<Employee> findAll(Sort sort) {
        return employeeRepository.findAll(sort);
    }

    @Override
    public List<Employee> findByOrderBySalaryAsc() {
        return employeeRepository.findByOrderBySalaryAsc();
    }

    @Override
    public List<Employee> findByOrderBySalaryDesc() {
        return employeeRepository.findByOrderBySalaryDesc();
    }

    @Override
    public List<Employee> findBySalaryRange(int minSalary, int maxSalary) {
        return employeeRepository.findBySalaryRange(minSalary, maxSalary);
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

}

package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.synergisticit.domain.Employee;
import com.synergisticit.service.EmployeeService;

@Controller
public class EmployeeController {
    @Autowired EmployeeService employeeService;
    
    @RequestMapping("employees")
    public ResponseEntity<List<Employee>> findAll() {
        List<Employee> listOfEmployees = employeeService.findAll();
        System.out.println("---List of Employees---");
        for (Employee e : listOfEmployees) {
            System.out.println(e);
        }
        return new ResponseEntity<List<Employee>>(listOfEmployees, HttpStatus.FOUND);
    }
    
    @RequestMapping("employee0")
    public ResponseEntity<Employee> findById() {
        Employee e = employeeService.findById(0);
        System.out.println("Employee Found: " + e);
        return new ResponseEntity<Employee>(e, HttpStatus.FOUND);
    }
    
    @RequestMapping("employee")
    public ResponseEntity<Employee> findById(@RequestParam int empid) {  // param is case-sensitive here
        Employee e = employeeService.findById(empid);
        System.out.println("Employee Found " + e);
        return new ResponseEntity<Employee>(e, HttpStatus.FOUND);
    }
    
}

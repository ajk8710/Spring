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
public class EmployeeController2 {
    
    @Autowired EmployeeService employeeService;
    
    // http://localhost:8080/findByName?name=Tomato
    @RequestMapping("findByName")
    public ResponseEntity<List<Employee>> findEmployeeByName(@RequestParam String name) {
        List<Employee> employees = employeeService.findByName(name);
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.FOUND);
    }
    
    // http://localhost:8080/findByDesignation?designation=food
    @RequestMapping("findByDesignation")
    public ResponseEntity<List<Employee>> findByDesignation(@RequestParam String designation) {
        List<Employee> employees = employeeService.findByDesignation(designation);
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.FOUND);
    }
    
    // http://localhost:8080/getByDesignation?designation=food
    @RequestMapping("getByDesignation")
    public ResponseEntity<List<Employee>> getByDesignation(@RequestParam String designation) {
        List<Employee> employees = employeeService.getByDesignation(designation);
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.FOUND);
    }
    
    // http://localhost:8080/findBySalaryGT?salary=100000
    @RequestMapping("findBySalaryGT")
    public ResponseEntity<List<Employee>> findBySalaryGreaterThan(@RequestParam int salary) {
        List<Employee> employees = employeeService.findBySalaryGreaterThan(salary);
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);  // FOUND or OK, either is fine
    }
    
    // http://localhost:8080/findBySalaryLT?salary=100000
    @RequestMapping("findBySalaryLT")
    public ResponseEntity<List<Employee>> findBySalaryLessThan(@RequestParam int salary) {
        List<Employee> employees = employeeService.findBySalaryLessThan(salary);
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }
    
    // http://localhost:8080/findBySalaryBT?sal1=70000&sal2=90000
    @RequestMapping("findBySalaryBT")  // Between is inclusive in SQL
    public ResponseEntity<List<Employee>> findBySalaryBetween(@RequestParam int sal1, int sal2) {
        List<Employee> employees = employeeService.findBySalaryBetween(sal1, sal2);
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }
    
    // http://localhost:8080/findByName2?name=Tomato
    @RequestMapping("findByName2")  // Between is inclusive in SQL
    public ResponseEntity<List<Employee>> findByName2(@RequestParam String name) {
        List<Employee> employees = employeeService.findByName2(name);
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }
    
    // http://localhost:8080/findByNameLike?name=ma
    @RequestMapping("findByNameLike")
    public ResponseEntity<List<Employee>> findByNameLike(@RequestParam String name) {
        List<Employee> employees = employeeService.findByNameLike(name);
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }
    
    // http://localhost:8080/findByNameLike?name=ma
    @RequestMapping("findByNameLike2")
    public ResponseEntity<List<Employee>> findByNameLike2(@RequestParam String name) {
        List<Employee> employees = employeeService.findByNameLike2(name);
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }
    
    // http://localhost:8080/findByNameLikeNativeQuery?name=ma
    @RequestMapping("findByNameLikeNativeQuery")
    public ResponseEntity<List<Employee>> findByNameLikeNativeQuery(@RequestParam String name) {
        List<Employee> employees = employeeService.findByNameLikeNativeQuery(name);
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }
    
    // http://localhost:8080/updateSalaries?byPercent=20&minSal=90000
    @RequestMapping("updateSalaries")
    public ResponseEntity<String> updateSalaries(@RequestParam int byPercent, int minSal) {
        Integer recordsUpdated = employeeService.updateSalaries(byPercent, minSal);
        return new ResponseEntity<String>("Records Updated: " + recordsUpdated, HttpStatus.OK);
    }
    
}

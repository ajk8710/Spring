package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    
    // http://localhost:8080/deleteByEmpId?empId=
    @RequestMapping("deleteByEmpId")
    public ResponseEntity<String> deleteByEmpId(@RequestParam int empId) {
        if (employeeService.existsByEmpId(empId)) {
            employeeService.deleteByEmpId(empId);
            return new ResponseEntity<String>("Deleted employee with empId=" + empId, HttpStatus.GONE);
        }
        else {
            return new ResponseEntity<String>("There is no employee with empId=" + empId, HttpStatus.GONE);
        }
    }
    
    // http://localhost:8080/getById?empId=
    @RequestMapping("getById")
    public ResponseEntity<?> getById(@RequestParam int empId) {
        Employee e = employeeService.getById(empId);
        if (e != null) {
            return new ResponseEntity<Employee>(e, HttpStatus.FOUND);
        }
        else {
            return new ResponseEntity<String>("There is no employee with empId=" + empId, HttpStatus.NOT_FOUND);
        }
    }
    
    // http://localhost:8080/findByNameAndDesignation?name=Tomato&designation=Food
    @RequestMapping("findByNameAndDesignation")
    public ResponseEntity<?> findByNameAndDesignation(@RequestParam String name, String designation) {
        Employee e = employeeService.findByNameAndDesignation(name, designation);
        if (e != null) {
            return new ResponseEntity<Employee>(e, HttpStatus.FOUND);
        }
        else {
            return new ResponseEntity<String>("There is no employee with name="+name+" and designation="+designation, HttpStatus.NOT_FOUND);
        }
    }
    
    // http://localhost:8080/findByDesignationAndSalary?designation=Food&salary=99999
    @RequestMapping("findByDesignationAndSalary")
    public ResponseEntity<?> findByDesignationAndSalary(@RequestParam String designation, int salary) {
        List<Employee> employees = employeeService.findByDesignationAndSalary(designation, salary);
        if (employees.size() > 0) {
            return new ResponseEntity<List<Employee>>(employees, HttpStatus.FOUND);
        }
        else {
            return new ResponseEntity<String>("There is no employee with designation="+designation+" and salary="+salary, HttpStatus.NOT_FOUND);
        }
    }
    
    // http://localhost:8080/insertEmployee?empId=30&name=soda&designation=Drink&salary=100000&insured=true
    @RequestMapping("insertEmployee")
    public ResponseEntity<String> insertEmployee(@RequestParam int empId, String name, String designation, int salary, boolean insured) {
        employeeService.insertEmployee(empId, name, designation, salary, insured);
        return new ResponseEntity<String>("Employee created with empId="+empId, HttpStatus.GONE);
    }
    
    // http://localhost:8080/findEmployeeSortedByName
    @RequestMapping("findEmployeeSortedByName") 
    public ResponseEntity<List<String>> findEmployeeSortedByName() {
        List<String> names = employeeService.findAllOrderByName();
        return new ResponseEntity<List<String>>(names, HttpStatus.OK);
    }
    
    // http://localhost:8080/findAll?sortedBy=designation
    @RequestMapping("findAll")
    public ResponseEntity<List<Employee>> findAll(@RequestParam String sortedBy) {
        List<Employee> employees = employeeService.findAll(Sort.by(sortedBy));
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }
    
    // http://localhost:8080/findByOrderBySalaryAsc
    @RequestMapping("findByOrderBySalaryAsc")
    public ResponseEntity<String> findByOrderBySalaryAsc() {
        List<Employee> employees = employeeService.findByOrderBySalaryAsc();
        
        StringBuilder sb = new StringBuilder();
        for (Employee e: employees) {
            sb.append(e.getEmpId() + ", " + e.getName() + ", " + e.getSalary() + "<br/>");
        }
        
        return new ResponseEntity<String>(sb.toString(), HttpStatus.ACCEPTED);
    }
    
    // http://localhost:8080/findByOrderBySalaryDesc
    @RequestMapping("findByOrderBySalaryDesc")
    public ResponseEntity<String> findByOrderBySalaryDesc() {
        List<Employee> employees = employeeService.findByOrderBySalaryDesc();
        
        StringBuilder sb = new StringBuilder();
        for (Employee e: employees) {
            sb.append(e.getEmpId() + ", " + e.getName() + ", " + e.getSalary() + "<br/>");
        }
        
        return new ResponseEntity<String>(sb.toString(), HttpStatus.ACCEPTED);
    }
    
    // http://localhost:8080/findBySalaryRange?minSalary=99999&maxSalary=99999
    @RequestMapping("findBySalaryRange")
    public ResponseEntity<String> findBySalaryRange(@RequestParam int minSalary, int maxSalary) {
        List<Employee> employees = employeeService.findBySalaryRange(minSalary, maxSalary);
        
        StringBuilder sb = new StringBuilder();
        for (Employee e: employees) {
            sb.append(e.getEmpId() + ", " + e.getName() + ", " + e.getSalary() + "<br/>");
        }
        
        return new ResponseEntity<String>(sb.toString(), HttpStatus.ACCEPTED);
    }
    
    // http://localhost:8080/findAllEmp?pageNo=0&pageSize=3&sortedBy=empId
    @RequestMapping("findAllEmp")  // pageNo starts at 0, pageSize is number of employees shown per page
    public ResponseEntity<List<Employee>> findAll(@RequestParam int pageNo, int pageSize, String sortedBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortedBy).descending());
        Page<Employee> pagedEmployees = employeeService.findAll(pageable);
        List<Employee> employees = pagedEmployees.getContent();
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.ACCEPTED);
    }
    
}

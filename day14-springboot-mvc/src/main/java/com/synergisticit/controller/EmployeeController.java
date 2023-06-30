package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    // @RequestMapping("employee")
    // @RequestMapping(value="employee")  // by default, the RequestMapping is a GetMapping (i.e. request is mapped to HTTP Get method)
    // @RequestMapping(value="employee", method=RequestMethod.GET)
    // @GetMapping("employee")
    @GetMapping(value="employee")  // http://localhost:8080/employee?empid=0
    public ResponseEntity<Employee> findById(@RequestParam int empid) {  // param name empid is case-sensitive here
        Employee e = employeeService.findById(empid);
        System.out.println("Employee Found " + e);
        return new ResponseEntity<Employee>(e, HttpStatus.FOUND);
    }
    
    @GetMapping("sum")  // http://localhost:8080/sum?i1=12&i2=10
    public ResponseEntity<String> sum(@RequestParam int i1, int i2) {
        int sum = i1 + i2;
        String str = "Sum of " + i1 + " and " + i2 + " is " + sum;
        return new ResponseEntity<String>(str, HttpStatus.OK);
    }
    
    @GetMapping("employeeForm")
    public String employeeForm(Model model) {
        System.out.println("======employeeForm()======");
        List<Employee> listOfEmployees = employeeService.findAll();
        model.addAttribute("listOfEmployees", listOfEmployees);  // adding variable employees to the model
        return "employeeForm";  // ViewName
    }
        
    @RequestMapping(value="saveEmployee", method=RequestMethod.POST)
    public String savesTheEmployee(@RequestParam int empid, String name, String designation, int salary) {
        System.out.println("Saving the employee: " + empid + " " + name + " " + designation + " " + salary);
        Employee e = new Employee(empid, name, designation, salary);
        employeeService.save(e);
        return "employeeForm";  // controller sending the name of the view
    }

}

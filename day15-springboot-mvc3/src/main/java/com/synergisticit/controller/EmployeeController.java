package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Employee;
import com.synergisticit.service.EmployeeService;

@Controller
public class EmployeeController {
    
    // Without @Qualifer,
    // If more than one bean of the same type is available in the container,
    // the framework will throw NoUniqueBeanDefinitionException
    @Qualifier("employeeService")
    @Autowired EmployeeService employeeService;
    
    @Qualifier("employeeServiceRepo")
    @Autowired EmployeeService employeeServiceRepo;
    
    @RequestMapping("employees")
    public ResponseEntity<List<Employee>> findAll() {
        // List<Employee> listOfEmployees = employeeService.findAll();
        List<Employee> listOfEmployees = employeeServiceRepo.findAll();
        
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
        
        // List<Employee> listOfEmployees = employeeService.findAll();
        List<Employee> listOfEmployees = employeeServiceRepo.findAll();
        model.addAttribute("listOfEmployees", listOfEmployees);  // adding variable employees to the model
        
        return "employeeForm";  // ViewName
    }

//    == Using @RequestParam ==
//    @RequestMapping(value="saveEmployee", method=RequestMethod.POST)
//    public String savesTheEmployee(@RequestParam int empid, String name, String designation, int salary, Model model) {
//        System.out.println("Saving the employee: " + empid + " " + name + " " + designation + " " + salary);
//        
//        if (employeeService.findById(empid) == null) {
//            Employee e = new Employee(empid, name, designation, salary);
//            employeeService.save(e);
//        }
//        else {
//            employeeService.updateById(empid, designation, salary);
//        }
//        
//        List<Employee> listOfEmployees = employeeService.findAll();
//        model.addAttribute("listOfEmployees", listOfEmployees);
//        
//        return "employeeForm";  // controller sending the name of the view
//    }
    
//  == Using @ModelAttribute ==
    @RequestMapping(value="saveEmployee", method=RequestMethod.POST)
    public String savesTheEmployee(@ModelAttribute Employee e, Model model) {
        System.out.println("Saving the employee: " + e);

        if (employeeService.findById(e.getEmpId()) == null) {
            employeeService.save(e);
        }
        else {
            employeeService.updateById(e.getEmpId(), e.getDesignation(), e.getSalary());
        }
        
        List<Employee> listOfEmployees = employeeService.findAll();
        model.addAttribute("listOfEmployees", listOfEmployees);
        
        return "employeeForm";  // controller sending the name of the view
    }

//    == Using @RequestParam ==
//    @RequestMapping("delete")
//    public String deletesTheEmployee(@RequestParam int empid, Model model) {
//        employeeService.deleteById(empid);
//        
//        List<Employee> listOfEmployees = employeeService.findAll();
//        model.addAttribute("listOfEmployees", listOfEmployees);
//        
//        return "employeeForm";
//    }
    
//    == Using @ModelAttribute ==
//    @RequestMapping("delete")
//    public String deletesTheEmployee(Employee e, Model model) {
//        System.out.println("Delete: " + e);  // it creates object with what is provided as request param (only knows empId)
//        // Employee [empId=6, name=null, designation=null, salary=0]
//        
//        employeeService.deleteById(e.getEmpId());
//        
//        List<Employee> listOfEmployees = employeeService.findAll();
//        model.addAttribute("listOfEmployees", listOfEmployees);
//        
//        return "employeeForm";
//    }
    
//  == Using ModelAndView instead of Model ==
    @RequestMapping("delete")
    public ModelAndView deletesTheEmployee(Employee e) {
        System.out.println("Delete: " + e);
        
        employeeService.deleteById(e.getEmpId());
        
        List<Employee> listOfEmployees = employeeService.findAll();
        
        ModelAndView mav = new ModelAndView("employeeForm");
        mav.addObject("listOfEmployees", listOfEmployees);
        
        return mav;
    }

//    == Using @RequestParam ==
//    @RequestMapping("update")
//    public String updatesTheEmployee(@RequestParam int empid, Model model) {
//        
//        Employee retrievedEmployee = employeeService.findById(empid);
//        model.addAttribute("retrievedEmployee", retrievedEmployee);
//        
//        List<Employee> listOfEmployees = employeeService.findAll();
//        model.addAttribute("listOfEmployees", listOfEmployees);
//        
//        return "employeeForm";
//    }
    
//    == Using @ModelAttribute ==
//    @RequestMapping("update")
//    public String updatesTheEmployee(Employee e, Model model) {
//        System.out.println("Update: " + e);  // it creates object with what is provided as request param (only knows empId)
//        // Employee [empId=6, name=null, designation=null, salary=0]
//        
//        Employee retrievedEmployee = employeeService.findById(e.getEmpId());
//        model.addAttribute("retrievedEmployee", retrievedEmployee);
//        
//        List<Employee> listOfEmployees = employeeService.findAll();
//        model.addAttribute("listOfEmployees", listOfEmployees);
//        
//        return "employeeForm";
//    }
    
//  == Using ModelMap instead of Model ==
    @RequestMapping("update")
    public String updatesTheEmployee(Employee e, ModelMap modelMap) {  // ModelMap is map to add key value pairs to model
        System.out.println("Update: " + e);
        
        Employee retrievedEmployee = employeeService.findById(e.getEmpId());
        List<Employee> listOfEmployees = employeeService.findAll();
        
        modelMap.addAttribute("retrievedEmployee", retrievedEmployee).addAttribute("listOfEmployees", listOfEmployees);
        
        return "employeeForm";
    }

}

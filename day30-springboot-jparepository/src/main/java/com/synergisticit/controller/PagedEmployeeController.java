package com.synergisticit.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.synergisticit.domain.Employee;
import com.synergisticit.domain.Gender;
import com.synergisticit.repository.EmployeeRepository;

@Controller
public class PagedEmployeeController {
    
    @Autowired EmployeeRepository employeeRepository;
    
    // http://localhost:8080/pagedEmployee?pageNo=0&pageSize=3&sortedBy=empId
    @RequestMapping("pagedEmployee")
    public String findThePagedEmployees(Employee e, @RequestParam(required=false) Integer pageNo, @RequestParam(required=false) Integer pageSize, 
            @RequestParam(required=false) String sortedBy, Model model) {
        Pageable pageable;
        System.out.println("pageNo: " + pageNo + ", pageSize: " + pageSize + ", sortedBy: " + sortedBy);
        pageNo = 0;
        pageSize = 3;
        sortedBy = "empId";
        pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortedBy));
        
        Page<Employee> pagedEmployees = employeeRepository.findAll(pageable);
        List<Employee> employeesOnPage = pagedEmployees.getContent();
        
        model.addAttribute("employeesOnPage", employeesOnPage);
        model.addAttribute("totalPages", pagedEmployees.getTotalPages());
        model.addAttribute("sortedBy", sortedBy);
        model.addAttribute("pageSize", pageSize);
        
        modelData(model);
        return "employeeList";
    }
    
    @RequestMapping(value="updateEmployeeOnPage")
    public String updateEmployee(Employee employee, @RequestParam(required=false) Integer pageNo, 
            @RequestParam(required=false) Integer pageSize, @RequestParam(required=false) String sortedBy, 
            Model model) {  // user object with userid and no other field passed in through route param
        System.out.println("Updating: " + employee);
        
        employee = employeeRepository.findById(employee.getEmpId()).get();  // search for the userid in db
        
        // with path="fieldName" (instead of name="fieldName") on jsp file, it's can be wired without value="${employee.fieldName}"
        System.out.println("Updating: " + employee);
        model.addAttribute("employee", employee);
        
        modelData(model);
        
        //==========================================================
        Pageable  pageable = null;
        // if(pageNo != null && pageSize != null) {
        pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortedBy));
        // }
        
        Page<Employee> pagedEmployees = employeeRepository.findAll(pageable);
        List<Employee> employeesOnPage = pagedEmployees.getContent();
        
        model.addAttribute("employeesOnPage", employeesOnPage);
        model.addAttribute("totalPages",pagedEmployees.getTotalPages());
        
        model.addAttribute("sortedBy", "empId");
        model.addAttribute("pageSize", 3);
        
        modelData(model);
        //==========================================================
        
        return "employeeList";
    }
    
    @RequestMapping(value="deleteEmployeeOnPage")
    public String deleteEmployee(Employee employee, Model model) {  // user object with userid and no other field passed in through route param
        System.out.println("Deleting: " + employee);
        employeeRepository.deleteById(employee.getEmpId());
        
        modelData(model);    
        return "employeeList";
    }
    
    public void modelData(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        
        String[] hobbies = {"Fishing", "Swimming", "Soccer", "Guitar", "Piano", "Gym"};
        model.addAttribute("hobbies", hobbies);
        
        List<String> spokenLanguages = new ArrayList<>();
        spokenLanguages.add("English");
        spokenLanguages.add("French");
        spokenLanguages.add("Korean");
        spokenLanguages.add("Spanish");
        model.addAttribute("spokenLanguages", spokenLanguages);
        
        Set<String> skills = new HashSet<>();
        skills.add("java");
        skills.add("MySQL");
        skills.add("Spring Boot");
        skills.add("MERN STACK");
        model.addAttribute("skills", skills);
        
        model.addAttribute("genders", Gender.values());
    }
    
}

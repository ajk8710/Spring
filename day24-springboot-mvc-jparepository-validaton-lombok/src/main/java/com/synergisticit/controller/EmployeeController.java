package com.synergisticit.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Employee;
import com.synergisticit.domain.Gender;
import com.synergisticit.service.EmployeeService;
import com.synergisticit.validation.EmployeeValidator;

import jakarta.validation.Valid;

@Controller
public class EmployeeController {
    
    @Autowired EmployeeService employeeService;
    @Autowired EmployeeValidator employeeValidator;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(employeeValidator);
    }
    
    @RequestMapping("employeeForm")
    public String employeeForm(Employee employee, Model model) {  // Employee as param is required since it is used in jsp file
        
        modelData(model);
        /*
        model.addAttribute("employees", employeeService.findAll());
        
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
        */
        
        return "employeeForm";
    }
    
    @RequestMapping(value="saveEmployee")
    public String saveEmployee(@Valid Employee employee, BindingResult br, Model model) {  // jpa repository automatically checks if record already exists
        // employeeValidator.validate(employee, br);
        
        System.out.println("Saving: " + employee);
        
        if (!br.hasErrors()) {
            employeeService.save(employee);
        }
        
        modelData(model);
        /*
        model.addAttribute("employees", employeeService.findAll());
        
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
        */
        
        return "employeeForm";
    }
    
    @RequestMapping(value="updateEmployee")
    public String updateEmployee(Employee employee, Model model) {  // user object with userid and no other field passed in through route param
        System.out.println("Updating: " + employee);
        Employee retrievedEmployee = employeeService.findById(employee.getEmpId());  // search for the userid in db
        System.out.println("Updating: " + retrievedEmployee);
        model.addAttribute("retrievedEmployee", retrievedEmployee);
        
        modelData(model);
        /*
        model.addAttribute("employees", employeeService.findAll());
        
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
        */
        
        return "employeeForm";
    }
    
    @RequestMapping(value="deleteEmployee")
    public String deleteEmployee(Employee employee, Model model) {  // user object with userid and no other field passed in through route param
        System.out.println("Deleting: " + employee);
        employeeService.deleteById(employee.getEmpId());
        
        modelData(model);
        /*
        model.addAttribute("employees", employeeService.findAll());
        
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
        */
        
        return "employeeForm";
    }
    
    public void modelData(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        
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

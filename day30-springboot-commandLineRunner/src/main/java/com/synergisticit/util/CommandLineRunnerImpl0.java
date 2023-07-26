package com.synergisticit.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.synergisticit.domain.Employee;
import com.synergisticit.service.EmployeeService;

// What’s in command line runner runs right after server runs, in the order given
@Order(value=3)
@Component
public class CommandLineRunnerImpl0 implements CommandLineRunner {
    @Autowired EmployeeService employeeService;
    
    @Override
    public void run(String... args) throws Exception {
        try {
            employeeService.insertEmployee(100, "Chicken", "Meat", 80000, false);
        }
        catch (Exception e) {
            System.out.println("Employee with given id already exists");
        }
        Employee e = employeeService.findById(100);
        System.out.println("Employee saved: " + e.getEmpId() + ", " + e.getName() + ", " + e.getDesignation());
        
    }

}

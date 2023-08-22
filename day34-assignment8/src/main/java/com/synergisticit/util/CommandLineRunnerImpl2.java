package com.synergisticit.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.synergisticit.domain.EmployeeAug18;
import com.synergisticit.repository.EmployeeAug18Repository;

@Order(value=2)
@Component
public class CommandLineRunnerImpl2 implements CommandLineRunner {
    
    @Autowired EmployeeAug18Repository employeeAug18Repository;
    
    @Override
    public void run(String... args) throws Exception {
        // Find the employees sorted on last name
        List<EmployeeAug18> empsSortedByLastName = employeeAug18Repository.findAll(Sort.by("lastName"));
        System.out.println(" == Employees Sorted by Lastname ==");
        for (EmployeeAug18 e : empsSortedByLastName) {
            System.out.println(e.getEmpId() + " " + e.getFirstName() + " " + e.getLastName() + " " + e.getDesignation() + " " + e.getSalary());
        }
        System.out.println();
    }
    
}

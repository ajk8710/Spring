package com.synergisticit.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.synergisticit.domain.EmployeeAug18;
import com.synergisticit.repository.EmployeeAug18Repository;

@Order(value=3)
@Component
public class CommandLineRunnerImpl3 implements CommandLineRunner {
    
    @Autowired EmployeeAug18Repository employeeAug18Repository;
    
    @Override
    public void run(String... args) throws Exception {
        // Find the employees sorted on ascending order of their firstName
        List<EmployeeAug18> empsSortedByFirstNameAsc = employeeAug18Repository.findByOrderByFirstNameAsc();
        System.out.println(" == Employees Sorted by Firstname Ascending ==");
        for (EmployeeAug18 e : empsSortedByFirstNameAsc) {
            System.out.println(e.getEmpId() + " " + e.getFirstName() + " " + e.getLastName() + " " + e.getDesignation() + " " + e.getSalary());
        }
        System.out.println();
    }
    
}

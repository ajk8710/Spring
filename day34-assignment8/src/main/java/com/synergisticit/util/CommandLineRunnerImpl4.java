package com.synergisticit.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.synergisticit.domain.EmployeeAug18;
import com.synergisticit.repository.EmployeeAug18Repository;

@Order(value=4)
@Component
public class CommandLineRunnerImpl4 implements CommandLineRunner {
    
    @Autowired EmployeeAug18Repository employeeAug18Repository;
    
    @Override
    public void run(String... args) throws Exception {
        // Find employee with maximum empId
        EmployeeAug18 empWithMaxId = employeeAug18Repository.findEmployeeWithMaxId();
        System.out.println(" == Employee with max empId ==");
        System.out.println(empWithMaxId.getEmpId() + " " + empWithMaxId.getFirstName() + " " + empWithMaxId.getLastName() + " " + empWithMaxId.getDesignation() + " " + empWithMaxId.getSalary());
        System.out.println();
    }
    
}

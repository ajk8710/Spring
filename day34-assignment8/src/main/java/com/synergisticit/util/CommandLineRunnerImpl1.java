package com.synergisticit.util;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.synergisticit.domain.EmployeeAug18;
import com.synergisticit.repository.EmployeeAug18Repository;

@Order(value=1)
@Component
public class CommandLineRunnerImpl1 implements CommandLineRunner {
    
    @Autowired EmployeeAug18Repository employeeAug18Repository;

    @Override
    public void run(String... args) throws Exception {
        List<EmployeeAug18> employees = employeeAug18Repository.findAll();
        System.out.println(" == Employees ==");
        for (EmployeeAug18 e : employees) {
            System.out.println(e.getEmpId() + " " + e.getFirtsName() + " " + e.getLastName());
        }
        System.out.println();

    }

}

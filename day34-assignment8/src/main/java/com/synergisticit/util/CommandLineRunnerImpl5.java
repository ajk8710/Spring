package com.synergisticit.util;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.synergisticit.domain.EmployeeAug18;
import com.synergisticit.repository.EmployeeAug18Repository;

@Order(value=5)
@Component
public class CommandLineRunnerImpl5 implements CommandLineRunner {
    
    @Autowired EmployeeAug18Repository employeeAug18Repository;
    
    @Override
    public void run(String... args) throws Exception {
        // Find employees whose salary is between a given range
        Scanner input = new Scanner(System.in);
        System.out.println("= Find Employees with Salary Between =");
        System.out.print("Enter min salary (inclusive): ");
        double minSalary;
        minSalary = input.nextDouble();
        System.out.print("Enter max salary (inclusive): ");
        double maxSalary = input.nextDouble();
        
        List<EmployeeAug18> empsWithSalaryBetween = employeeAug18Repository.findEmpsWithSalaryBetween(minSalary, maxSalary);
        System.out.println(" == Employees with Salary Between $" + minSalary + " & $" + maxSalary + " ==");
        for (EmployeeAug18 e : empsWithSalaryBetween) {
            System.out.println(e.getEmpId() + " " + e.getFirstName() + " " + e.getLastName() + " " + e.getDesignation() + " " + e.getSalary());
        }
        System.out.println();
        input.close();
    }
    
}

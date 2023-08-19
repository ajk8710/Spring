package com.synergisticit.util;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.synergisticit.domain.EmployeeAug18;
import com.synergisticit.repository.EmployeeAug18Repository;

@Order(value=0)
@Component
public class CommandLineRunnerImpl0 implements CommandLineRunner {
    
    @Autowired EmployeeAug18Repository employeeAug18Repository;

    @Override
    public void run(String... args) throws Exception {
        List<EmployeeAug18> employees = employeeAug18Repository.findAll();
        System.out.println(" == Employees ==");
        for (EmployeeAug18 e : employees) {
            System.out.println(e.getEmpId() + " " + e.getFirtsName() + " " + e.getLastName());
        }
        System.out.println();
        
        Scanner input = new Scanner(System.in);
        String choice = "0";
        while (!choice.equals("4")) {
            System.out.print("Enter 1 for save, 2 to update, 3 delete, 4 to quit: ");
            
            choice = input.next();

            if (choice.equals("1")) {
                System.out.print("Please enter id: ");
                int id = input.nextInt();
                System.out.print("Please enter first name: ");
                String firstName = input.next();
                System.out.print("Please enter second name: ");
                String secondName = input.next();
                System.out.print("Please enter designation: ");
                String designation = input.next();
                System.out.print("Please enter salary: ");
                double salary = input.nextDouble();
                
                EmployeeAug18 newEmp = new EmployeeAug18(id, firstName, secondName, designation, salary);
                employeeAug18Repository.save(newEmp);
                
            }
            else if (choice.equals("2")) {
                System.out.print("Please enter emp id to update: ");
                int id = input.nextInt();
                System.out.print("Please enter first name: ");
                String firstName = input.next();
                System.out.print("Please enter second name: ");
                String secondName = input.next();
                System.out.print("Please enter designation: ");
                String designation = input.next();
                System.out.print("Please enter salary: ");
                double salary = input.nextDouble();
                
                EmployeeAug18 newEmp = new EmployeeAug18(id, firstName, secondName, designation, salary);
                employeeAug18Repository.save(newEmp);
            }
            else if (choice.equals("3")) {
                System.out.print("Please enter emp id to delete: ");
                int id = input.nextInt();
                employeeAug18Repository.deleteById(id);
            }
            else if (choice.equals("4")) {
                System.out.println("Thank you. Bye.");
            }
            else {
                System.out.println("Please enter valid choice");
            }
            
        }

    }

}

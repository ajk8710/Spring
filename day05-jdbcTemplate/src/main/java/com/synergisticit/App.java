package com.synergisticit;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.synergisticit.domain.Employee;
import com.synergisticit.dao.EmployeeDao;

public class App {
    /*
     * Inversion of Control (IoC): Control of creating object is inverted
     * Design pattern where:
     * an application does not create objects but,
     * the application asks some other software/tool to create an object
     * - Outsourcing the creation of an object - done by third party
     * 
     * Dependency Injection (DI): A process of providing the dependent object
     * Creation of object is dependent on another object, which may be property of the object being created.
     * DI can be achieved by a constructor, a setter, a field
     * (Injects properties using constructor or setter - the property may be dependent on another object - injecting that another object)
     * (EX: Employee is dependent on Address, then Address may be dependent on some other Object, etc)
     * 
     * ApplicationContext is the custodian of all the beans. It creates, manages and destroys the beans on demand
     * ApplicationContext is also called as container, Spring container, IoC container
     * 
     * Bean: Any java object that is created, maintained, and managed by the Spring container
     * 
     * Scope of a bean: Singleton (default), prototype. In web Applications, there are more scopes like request, etc.
     * Bean in Singleton scope: Only one instance of the bean is used to serve all the requests for that bean
     * Bean in Prototype scope: As many instances of the bean are created as many times requests for that bean is sent
     * 
     * Auto-Wiring: wires property with dependent beans (collaborating with dependent beans)
     *  - an instruction to Spring container, providing dependent object (employee is dependent on address) without explicit codes
     * byName: the property's name (address) matches bean definition name (address)
     * byType: the property's class (Address) matches bean definition class (Address)
     * constructor: the property's class in constructor argument (Address) matches bean definition class (Address)
     * default = no
    */

    public static void main(String[] args) {
        
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        String[] beans = context.getBeanDefinitionNames();
        for (String beanName : beans) {
        	System.out.println(beanName);
        }

        EmployeeDao employeeDao = (EmployeeDao) context.getBean("employeeDao");
        
        // Employee e = new Employee(1, "Pikachu", "Developer", 100000);
        // employeeDao.save(e);
        // Employee e = new Employee(2, "Raichu", "Developer", 150000);
        // employeeDao.save(e);
        // Employee e = new Employee(3, "Squirtle", "Developer", 100000);
        // employeeDao.save(e);
        // Employee e = new Employee(4, "Charmander", "Developer", 100000);
        // employeeDao.save(e);
        // Employee e = new Employee(5, "Pigeon", "Developer", 100000);
        // employeeDao.save2(e);
        
        // List<Employee> listOfEmployee = employeeDao.findAll();
        // for (Employee e : listOfEmployee) {
        //    System.out.println(e);
        // }
        
        // System.out.println(employeeDao.findById(1));
        // System.out.println(employeeDao.findById(2));
        
        // System.out.println(employeeDao.updateById(2, "Developer II", 150000));
        // System.out.println(employeeDao.deleteById(5));
        
        // Advantage of jdbcTemplate over jdbc:
        // With spring, you write less java code regarding jdbc, making database connections, and handling exceptions
        // Can focus more on business requirements
        
        context.close();
    }
    
}

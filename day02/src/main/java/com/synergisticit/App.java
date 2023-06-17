package com.synergisticit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.synergisticit.domain.Employee;
import com.synergisticit.domain.Address;

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
     * 
     * ApplicationContext is the custodian of all the beans. It creates, manages and destroys the beans on demand
     * ApplicationContext is also called as container, Spring container, IoC container
     * 
     * Bean: Any java object that is created, maintained, and managed by the Spring container
    */
    public static void main(String[] args) {
        
        // Legacy way of creating the Objects:
    	// The application itself is creating Objects
        Employee e1 = new Employee(1, "Pikachu", "Developer", 10000);
        System.out.println("e1: " + e1);
        
        Address address = new Address();
        address.setAddressLine1("House No.1");
        address.setAddressLine2("Road No.1");
        address.setCity("Pallet City");
        address.setState("NY");
        address.setCountry("USA");
        
        e1.setAddress(address);
        System.out.println("e1: " + e1);
        
        
        // Spring is used because of these features: Dependency Injection and Inversion of Control (DI and IoC)
        // Spring Bean: Any object that is created and managed by Spring framework
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println("e2: " + context.getBean("e2"));  // employee is dependent on address - dependency injection
        System.out.println("add2: " + context.getBean("add2"));
    }
    
}

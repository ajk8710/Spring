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
     * (Injects properties using constructor or setter - the property may be dependent on another object)
     * 
     * ApplicationContext is the custodian of all the beans. It creates, manages and destroys the beans on demand
     * ApplicationContext is also called as container, Spring container, IoC container
     * 
     * Bean: Any java object that is created, maintained, and managed by the Spring container
     * 
     * Scope of a bean: Singleton (default), prototype. In web Applications, there are more scopes like request, etc.
     * Bean in Singleton scope: Only one instance of the bean is used to serve all the requests for that bean
     * Bean in Prototype scope: As many instances of the bean are created as many times requests for that bean is sent
    */
    public static void main(String[] args) {
        
        // Legacy way of creating the Objects:
    	// The application itself is creating Objects
        Employee e1 = new Employee(1, "Pikachu", "Developer", 10000);
        // System.out.println("e1: " + e1);
        
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
        // Employee.afterPropertiesSet() called upon ClassPathXmlApplicationContext("beans.xml")
        // init-method called upon new ClassPathXmlApplicationContext("beans.xml")
        
        System.out.println("e2: " + context.getBean("e2"));  // employee is dependent on address - dependency injection
        System.out.println("add2: " + context.getBean("add2"));
        
        System.out.println("e3: " + context.getBean("e3"));
        System.out.println("e4: " + context.getBean("e4"));
        System.out.println("e5: " + context.getBean("e5"));
        
        int beanCount = context.getBeanDefinitionCount();  // count of all bean definitions
        System.out.println("Bean Count: " + beanCount);
        String [] beanNames = context.getBeanDefinitionNames();  // array of name of all bean definitions
        for (String beanName : beanNames) {
        	System.out.print(beanName + " ");
        }
        
        // check if contains bean
        System.out.println();
        System.out.print(context.containsBean("e2") + " ");
        System.out.print(context.containsBeanDefinition("e2") + " ");
        System.out.println(context.containsLocalBean("e2"));
        
        System.out.println("Application Name:" + context.getApplicationName());  // no name
        // System.out.println(context.getBean(Address.class));  // error if multiple bean of same class
        System.out.println(context.getBean("add2", Address.class));
        
        // get beans of a class
        String[] employeeBeanNames = context.getBeanNamesForType(Employee.class);
        for (String employeeBeanName : employeeBeanNames) {
        	System.out.print(employeeBeanName + " ");
        }
        
        System.out.println();
        String[] addressBeanNames = context.getBeanNamesForType(Address.class);
        for (String addressBeanName : addressBeanNames) {
        	System.out.print(addressBeanName + " ");
        }
        System.out.println();
        
        Employee e4Obj1 = (Employee) context.getBean("e4");
        System.out.println("e4Obj1: " + e4Obj1.hashCode());
        
        Employee e4Obj2 = (Employee) context.getBean("e4");
        System.out.println("e4Obj2: " + e4Obj2.hashCode());  // non-singleton (prototype) is not default
        
        Employee e5Obj1 = (Employee) context.getBean("e5");
        System.out.println("e5Obj1: " + e5Obj1.hashCode());
        
        Employee e5Obj2 = (Employee) context.getBean("e5");
        System.out.println("e5Obj2: " + e5Obj2.hashCode());  // singleton is default behavior
        
        Employee e3Obj1 = (Employee) context.getBean("e3");
        System.out.println("e3Obj1: " + e3Obj1.hashCode());
        
        Employee e3Obj2 = (Employee) context.getBean("e3");
        System.out.println("e3Obj2: " + e3Obj2.hashCode());
        
        context.close();  // ApplicationContext does not have close method, but AbstractApplicationConext does
        // Employee.destroy() called upon close
        // destroy-method called upon close
    }
    
}

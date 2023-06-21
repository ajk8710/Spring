package com.synergisticit;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.synergisticit.domain.Employee;
import com.synergisticit.dao.EmployeeDao;
import com.synergisticit.dao.EmployeeDao2;

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
        
        EmployeeDao2 employeeDao2 = (EmployeeDao2) context.getBean("employeeDao2");
        // employeeDao2.createEmployee(new Employee(5, "Onion", "Vegetables", 70000));
        
        // System.out.println(employeeDao2.getEmployeeByEmpId1(5));
        // System.out.println(employeeDao2.getEmployeeByEmpId2(5));
        // System.out.println(employeeDao2.getEmployeeByEmpId3(5));
        List<Employee> listofEmployees = employeeDao2.findAllEmployees();
        for (Employee e : listofEmployees) {
            System.out.println(e);
        }
        
        // System.out.println(employeeDao2.updateEmployeeByEmpId(5, "Food", 90000));
        
        // employeeDao2.deleteEmployeeByEmpId(5);
        
        context.close();
    }
    
}

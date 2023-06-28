package com.synergisticit;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.synergisticit.domain.Employee;
import com.synergisticit.service.EmployeeService;
import com.synergisticit.config.AppConfig;
import com.synergisticit.config.AppConfig2;

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
        
        // AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class, AppConfig2.class);  // it is aware of annotations
        // context.register(AppConfig2.class);
        
        String[] beans = context.getBeanDefinitionNames();
        for (String beanName : beans) {
        	System.out.println(beanName);
        }
        
        EmployeeService employeeService = (EmployeeService) context.getBean("employeeService3");
        
        List<Employee> listofEmployees = employeeService.findAll();
        // for (Employee e : listofEmployees) {
        //     System.out.println(e);
        // }
        
        // employeeService.save(new Employee(5, "Onion", "Vegetables", 70000));
        // employeeService.findById(0);
        // employeeService.updateById(5, "Food", 90000);
        // employeeService.deleteById(5);
        
        context.close();
        
    }
    
}

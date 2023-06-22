package com.synergisticit;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.synergisticit.dao.EmployeeDao;
import com.synergisticit.domain.Employee;

public class App {
    public static void main( String[] args ) {
        
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        String[] beans = context.getBeanDefinitionNames();
        for (String beanName : beans) {
            System.out.println(beanName);
        }
        
        EmployeeDao employeeDao = (EmployeeDao) context.getBean("employeeDao");
        
        Employee e3 = new Employee(3, "Onion", "Vegetables", 70000);
        Employee e4 = new Employee(4, "Carrot", "Vegetables", 70000);
        Employee e5 = new Employee(5, "Muscadine", "Fruits", 90000);
        Employee e6 = new Employee(6, "Cherry", "Fruits", 90000);
        
        // System.out.println(employeeDao.getEmployee(5));
        List<Employee> listOfEmployees = employeeDao.getAllEmployees();
        
        for (Employee e : listOfEmployees) {
            System.out.println(e);
        }
        
        // System.out.println(employeeDao.createEmployee(e5));
        // System.out.println(employeeDao.updateEmployee(5, "Big Fruits", 120000));
        // System.out.println(employeeDao.deleteEmployee(6));
        
        context.close();
    }
}

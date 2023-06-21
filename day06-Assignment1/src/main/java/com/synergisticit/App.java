package com.synergisticit;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.synergisticit.domain.Employee;
import com.synergisticit.dao.EmployeeDao;

public class App {
    public static void main( String[] args ) {
        
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        
        Employee e0 = (Employee) context.getBean("e0");
        Employee e1 = (Employee) context.getBean("e1");
        Employee e2 = (Employee) context.getBean("e2");
        
        EmployeeDao employeeDao = (EmployeeDao) context.getBean("employeeDao");
        
        // System.out.println(employeeDao.save(e0));
        // System.out.println(employeeDao.save(e1));
        // System.out.println(employeeDao.save(e2));
        // System.out.println(employeeDao.findAll());
        // System.out.println(employeeDao.findById(0));
        // System.out.println(employeeDao.updateById(2, "Fire Pokemon", 120000));
        // System.out.println(employeeDao.deleteById(2));
        // System.out.println(employeeDao.save(e2));
        
        
    }
    
}

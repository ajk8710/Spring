package com.synergisticit.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.Employee;

@Repository(value = "employeeDao3")
public class EmployeeDaoImpl3 implements EmployeeDao {
    
    @Autowired SessionFactory sessionFactory;
    Session session = null;

    @Override
    public void save(Employee e) {
        System.out.println("====== sessionFactory in employeeDao saves employee ======");
        
        try(Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            session.save(e);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error while opening sesison");
            ex.printStackTrace();
        }
    }

    @Override
    public void save2(Employee e) {
        // To do
    }

    @Override
    public List<Employee> findAll() {
        System.out.println("====== sessionFactory in employeeDao lists employee ======");
        
        List<Employee> listOfEmployees = new ArrayList<>();
        
        try(Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            listOfEmployees = session.createQuery("from Employee").list();  // Hibernate Query Language - Employee is name of class (must start with capital)
            for (Employee e : listOfEmployees) {
                System.out.println(e);
            }
        } catch (Exception ex) {
            System.out.println("Error while opening sesison");
            ex.printStackTrace();
        }
        return listOfEmployees;
    }

    @Override
    public Employee findById(int empId) {
        System.out.println("====== sessionFactory in employeeDao finds a employee ======");
        
        Employee e = null;
        
        try(Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            e = session.get(Employee.class, empId);  // Hibernate Query Language
            System.out.println(e);
        } catch (Exception ex) {
            System.out.println("Error while opening sesison");
            ex.printStackTrace();
        }
        return e;
    }

    @Override
    public Employee updateById(int empId, String designation, int salary) {
        System.out.println("====== sessionFactory in employeeDao updates a employee ======");
        
        Employee e = null;
        
        try(Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            e = session.get(Employee.class, empId);  // Hibernate Query Language
            e.setDesignation(designation);
            e.setSalary(salary);
            session.update("Employee", e);
            session.getTransaction().commit();
            
            e = session.get(Employee.class, empId);
            System.out.println(e);
        } catch (Exception ex) {
            System.out.println("Error while opening sesison");
            ex.printStackTrace();
        }
        return e;
    }

    @Override
    public int deleteById(int empId) {
        System.out.println("====== sessionFactory in employeeDao deletes a employee ======");
        
        Employee e = null;
        
        try(Session session = sessionFactory.openSession();) {
            session.beginTransaction();
            e = session.get(Employee.class, empId);  // Hibernate Query Language
            if (e != null ) {
                session.delete(e);
                session.getTransaction().commit();
                System.out.println("Deleted" + e);
                return 1;
            }
        } catch (Exception ex) {
            System.out.println("Error while opening sesison");
            ex.printStackTrace();
        }
        return 0;
    }

}

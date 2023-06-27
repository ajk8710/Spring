package com.synergisticit.domain;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee implements InitializingBean, DisposableBean {
    @Id int empId;
    private String name;
    private String designation;
    private int salary;
//    private Address address;
    
    public Employee() {  // no-arg constructor
    }
    
    public Employee(int empId, String name, String designation, int salary) {
        this.empId = empId;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }
    
//    public Employee(int empId, String name, String designation, int salary, Address address) {
//        this.empId = empId;
//        this.name = name;
//        this.designation = designation;
//        this.salary = salary;
//        this.address = address;
//    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }

    public void m1() {
    	System.out.println("The init-method m1() invoked");
    }
    
    public void m2() {
    	System.out.println("The destroy-method m2() invoked");
    }
    
    @Override
    public String toString() {
        return "Employee [empId=" + empId + ", name=" + name + ", designation=" + designation + ", salary=" + salary + "]";
    }

	@Override
	public void afterPropertiesSet() throws Exception {
		this.salary = this.salary + 50000;
		System.out.println("Employee.afterPropertiesSet() of initializing bean invoked");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("Employee.destroy() of initializing bean invoked");
		
	}
    
}
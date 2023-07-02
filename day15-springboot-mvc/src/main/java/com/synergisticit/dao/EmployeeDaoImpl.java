package com.synergisticit.dao;

import java.util.List;

import com.synergisticit.domain.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
    
@Repository(value = "EmployeeDao")
public class EmployeeDaoImpl implements EmployeeDao {
    
    @Autowired JdbcTemplate jdbcTemplate;

    @Override
    public void save(Employee e) {
        String sql = "INSERT INTO EMPLOYEE(EmpId, name, designation, salary) VALUES (?, ?, ?, ?);";
        int numRecordsAffected = jdbcTemplate.update(sql, new Object[] {e.getEmpId(), e.getName(), e.getDesignation(), e.getSalary()});
        System.out.println("Num of Records inserted: " + numRecordsAffected);
        System.out.println(sql);
    }

    @Override
    public List<Employee> findAll() {
        String query = "SELECT * FROM EMPLOYEE;";
        // from jdbc result, map each row to instance of Employee
        List<Employee> listOfEmployee = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Employee.class));
        return listOfEmployee;
    }

    @Override
    public Employee findById(int empId) {
        String query = "SELECT * FROM EMPLOYEE WHERE EMPID=?;";
        try {
            Employee retrievedEmp = jdbcTemplate.queryForObject(query, BeanPropertyRowMapper.newInstance(Employee.class), empId);
            return retrievedEmp;
        }
        catch (EmptyResultDataAccessException ex) {
            System.out.println("No result found: Returning null instead of EmptyResultDataAccessException");
            return null;
        }
    }

    @Override
    public Employee updateById(int empId, String designation, int salary) {
        String query = "UPDATE EMPLOYEE SET DESIGNATION=?, SALARY=? WHERE EMPID=?;";
        jdbcTemplate.update(query, new Object[] {designation, salary, empId});
        return findById(empId);
    }

    @Override
    public int deleteById(int empId) {
        String query = "DELETE FROM EMPLOYEE WHERE EMPID=?;";
        int numRecordsAffected = jdbcTemplate.update(query, new Object[] {empId});
        return numRecordsAffected;
    }

}

/*
On MySQL Command Line Client:
show databases;
create database springdb;
use springdb;
show tables;

create table springdb.employee (
    empId int primary key,
    name varchar(100) not null,
    designation varchar(100) not null,
    salary int
);

desc springdb.employee;  (or desc employee;)
*/

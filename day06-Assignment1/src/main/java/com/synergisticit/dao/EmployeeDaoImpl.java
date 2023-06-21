package com.synergisticit.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.synergisticit.domain.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

    JdbcTemplate jdbcTemplate;
    
    // default constructor auto generates since there is no explicit constructor
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  // make sure to have setter for all property
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public int save(Employee e) {
        String sql = "INSERT INTO EMPLOYEE2 (empId, name, designation, salary) VALUES (?, ?, ?, ?);";
        int numRecordsAffected = jdbcTemplate.update(sql, e.getEmpId(), e.getName(), e.getDesignation(), e.getSalary());
        return numRecordsAffected;
    }

    @Override
    public List<Employee> findAll() {
        String sql = "SELECT * FROM EMPLOYEE2;";
        List<Employee> listOfEmployee = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Employee.class));
        return listOfEmployee;
    }

    @Override
    public Employee findById(int empId) {
        String sql = "SELECT * FROM EMPLOYEE2 WHERE empId=?;";
        Employee e = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Employee.class), empId);
        return e;
    }

    @Override
    public Employee updateById(int empId, String designation, int salary) {
        String sql = "Update EMPLOYEE2 SET designation=?, salary=? WHERE empId=?;";
        jdbcTemplate.update(sql, designation, salary, empId);
        return findById(empId);
    }

    @Override
    public int deleteById(int empId) {
        String sql = "DELETE FROM EMPLOYEE2 WHERE empId=?;";
        int numRecordsAffected = jdbcTemplate.update(sql, empId);
        return numRecordsAffected;
    }
}

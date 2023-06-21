package com.synergisticit.dao;

import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.synergisticit.domain.Employee;

public class EmployeeDaoImpl2 implements EmployeeDao2 {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;  // ueses named parameters rather than traditional "?" placeholders
    
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int createEmployee(Employee e) {
        String sql = "INSERT INTO EMPLOYEE (empId, name, designation, salary) values (:empId, :name, :designation, :salary);";
        
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("empId", e.getEmpId());
        paramSource.addValue("name",  e.getName());
        paramSource.addValue("designation", e.getDesignation());
        paramSource.addValue("salary", e.getSalary());
        
        int numRowsAffected = namedParameterJdbcTemplate.update(sql, paramSource);
        return numRowsAffected;
    }

    @Override
    public Employee getEmployeeByEmpId(int empId) {  // getEmployeeByEmpId uses Map
        String sql = "SELECT * FROM EMPLOYEE WHERE empId=:empId;";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("empId", empId);
        
        // Can use BeanPropertyRowMapper
        // Employee e = namedParameterJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(Employee.class));
        
        // Or create Mapper by myself
        Employee e = namedParameterJdbcTemplate.queryForObject(sql, paramMap, new EmployeeRowMapper());
        return e;
    }
    
    @Override
    public Employee getEmployeeByEmpId2(int empId) {  // getEmployeeByEmpId2 uses MapSqlParameterSource instead of Map
        String sql = "SELECT * FROM EMPLOYEE WHERE empId=:empId;";
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("empId", empId);
        
        Employee e = namedParameterJdbcTemplate.queryForObject(sql, paramSource, new EmployeeRowMapper());
        return e;
    }
    
    @Override
    public String getEmployeeByEmpId3(int empId) {  // getEmployeeByEmpId3 uses Map and required type (this only accepts one column)
        String sql = "SELECT name FROM EMPLOYEE WHERE empId=:empId;";  // select one column
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("empId", empId);
        
        String name = namedParameterJdbcTemplate.queryForObject(sql, paramMap, String.class);  // this only accepts one column
        return name;
    }
    
    // Mapper class for Employee
    private class EmployeeRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            // rs.next();  // not required, should not have it - jdbc already handled for us
            int empId = rs.getInt("empId");
            String name = rs.getString("name");
            String designation = rs.getString("designation");
            int salary = rs.getInt("salary");
            return new Employee(empId, name, designation, salary);
        }
    }

    @Override
    public List<Employee> findAllEmployees() {
        String sql = "SELECT * FROM EMPLOYEE;";
        List<Employee> listOfEmployees = namedParameterJdbcTemplate.query(sql, new EmployeeRowMapper());
        return listOfEmployees;
    }

    @Override
    public Employee updateEmployeeByEmpId(int empId, String designation, int salary) {
        String sql = "UPDATE EMPLOYEE SET designation=:designation, salary=:salary WHERE empId=:empId;";
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("designation", designation);
        paramSource.addValue("salary", salary);
        paramSource.addValue("empId", empId);
        namedParameterJdbcTemplate.update(sql, paramSource);
        
        return getEmployeeByEmpId(empId);
    }

    @Override
    public int deleteEmployeeByEmpId(int empId) {
        String sql = "DELETE FROM EMPLOYEE WHERE empId=:empId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("empId", empId);
        int numRecordsAffected = namedParameterJdbcTemplate.update(sql, paramMap);
        
        return numRecordsAffected;
    }
    
}

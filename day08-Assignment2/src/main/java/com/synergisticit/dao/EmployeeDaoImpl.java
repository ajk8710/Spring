package com.synergisticit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.synergisticit.domain.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
    
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int createEmployee(Employee e) {
        String sql = "insert into employee2 (empId, name, designation, salary) values (:empId, :name, :designation, :salary)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("empId", e.getEmpId());
        params.addValue("name", e.getName());
        params.addValue("designation", e.getDesignation());
        params.addValue("salary", e.getSalary());
        
        int numRowsAffected = namedParameterJdbcTemplate.update(sql, params);
        return numRowsAffected;
    }

    @Override
    public Employee getEmployee(int empId) {
        String sql = "select * from employee2 where empId=:empId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("empId", empId);
        
        Employee e = namedParameterJdbcTemplate.queryForObject(sql, params, new EmployeeRowMapper());
        // Employee e = namedParameterJdbcTemplate.queryForObject(sql, params, BeanPropertyRowMapper.newInstance(Employee.class));
        return e;
    }

    @Override
    public List<Employee> getAllEmployees() {
        String sql = "select * from employee2";
        List<Employee> listOfEmployees = namedParameterJdbcTemplate.query(sql, new EmployeeRowMapper());
        // List<Employee> listOfEmployees = namedParameterJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Employee.class));
        return listOfEmployees;
    }
    
    // RowMapper for Employee or I can use BeanPropertyRowMapper
    private class EmployeeRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            int empId = rs.getInt("empId");
            String name = rs.getString("name");
            String designation = rs.getString("designation");
            int salary = rs.getInt("salary");
            return new Employee(empId, name, designation, salary);
        }
    }
    
    @Override
    public int updateEmployee(int empId, String designation, int salary) {
        String sql = "update employee2 set designation=:designation, salary=:salary where empId=:empId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("empId", empId);
        params.addValue("designation", designation);
        params.addValue("salary", salary);
        
        int numRowsAffected = namedParameterJdbcTemplate.update(sql, params);
        return numRowsAffected;
    }

    @Override
    public int deleteEmployee(int empId) {
        String sql = "delete from employee2 where empId=:empId";
        Map<String, Object> params = new HashMap<>();
        params.put("empId", empId);
        
        int numRowsAffected = namedParameterJdbcTemplate.update(sql, params);
        return numRowsAffected;
    }

}

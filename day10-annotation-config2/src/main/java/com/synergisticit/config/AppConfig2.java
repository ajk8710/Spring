package com.synergisticit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.synergisticit.dao.EmployeeDao;
import com.synergisticit.dao.EmployeeDao2;
import com.synergisticit.dao.EmployeeDaoImpl;
import com.synergisticit.dao.EmployeeDaoImpl2;
import com.synergisticit.service.EmployeeService;
import com.synergisticit.service.EmployeeServiceImpl;


@Configuration
public class AppConfig2 {

    @Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired JdbcTemplate jdbcTemplate;

    
    @Bean
    public EmployeeDao employeeDao() {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        employeeDao.setJdbcTemplate(jdbcTemplate);
        return employeeDao;
    }
    
    @Bean
    public EmployeeDao2 employeeDao2() {  // employeeDao2 has namedParameterJdbcTemplate
        EmployeeDaoImpl2 employeeDao2 = new EmployeeDaoImpl2();
        employeeDao2.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
        return employeeDao2;
    }
    
    @Bean
    public EmployeeService employeeService() {
        EmployeeService employeeService = new EmployeeServiceImpl();
        // no need to call setEmployeeDao or setEmployeeDao2 because @Autowired
        return employeeService;
    }
    
}

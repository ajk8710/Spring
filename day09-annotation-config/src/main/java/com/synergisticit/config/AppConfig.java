package com.synergisticit.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.synergisticit.dao.EmployeeDao;
import com.synergisticit.dao.EmployeeDao2;
import com.synergisticit.dao.EmployeeDaoImpl;
import com.synergisticit.dao.EmployeeDaoImpl2;
import com.synergisticit.service.EmployeeService;
import com.synergisticit.service.EmployeeServiceImpl;

@PropertySource(value="classpath:db.properties")
@Configuration
public class AppConfig {
    
    @Autowired
    Environment env;
    
    @Bean  // this is how to declare bean using java
    public DataSource dataSource() {
        System.out.println("Driver: " + env.getProperty("driver"));
        System.out.println("URL: " + env.getProperty("url"));
        System.out.println("username: " + env.getProperty("myusername"));
        System.out.println("password: " + env.getProperty("mypassword"));
        
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getProperty("driver"));
        ds.setUrl(env.getProperty("url"));
        ds.setUsername(env.getProperty("myusername"));
        ds.setPassword(env.getProperty("mypassword"));
        return ds;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }
    
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {  // namedParameterJdbcTemplate has data source
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource());
        return namedParameterJdbcTemplate;
    }
    
    @Bean
    public EmployeeDao employeeDao() {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        employeeDao.setJdbcTemplate(jdbcTemplate());
        return employeeDao;
    }
    
    @Bean
    public EmployeeDao2 employeeDao2() {  // employeeDao2 has namedParameterJdbcTemplate
        EmployeeDaoImpl2 employeeDao2 = new EmployeeDaoImpl2();
        employeeDao2.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate());
        return employeeDao2;
    }
    
    @Bean
    public EmployeeService employeeService() {
        EmployeeService employeeService = new EmployeeServiceImpl();
        // no need to call setEmployeeDao or setEmployeeDao2 because @Autowired
        return employeeService;
    }
    
}

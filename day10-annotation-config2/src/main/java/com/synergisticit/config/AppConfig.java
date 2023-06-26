package com.synergisticit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.mysql.cj.xdevapi.SessionFactory;
import jakarta.persistence.EntityManagerFactory;

@PropertySource(value="classpath:db.properties")
@Configuration
@ImportResource("classpath:beans2.xml")  // import dataSource bean from beans.xml
public class AppConfig {
    
    @Autowired
    Environment env;

    @Autowired DataSource dataSource;  // dataSource is in beans.xml
    @Autowired SessionFactory sessionFactory;
    
/*
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
*/

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
    
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {  // namedParameterJdbcTemplate has data source
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        return namedParameterJdbcTemplate;
    }
    
/*
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
*/
    
    @Bean
    public PlatformTransactionManager transactionManager() {
        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        return transactionManager;
    }
    
    /*
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager trxManager = new HibernateTransactionManager();
        trxManager.setSessionFactory(sessionFactory);
        //trxManager.setDataSource(dataSource);
        return trxManager;
    }
    */
    
    @Bean
    public EntityManagerFactory entityManager() {
             LocalContainerEntityManagerFactoryBean entityManager = new  LocalContainerEntityManagerFactoryBean();
             entityManager.setDataSource(dataSource);
             entityManager.setPackagesToScan("com.synergisticit.domain");
             entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
             entityManager.setJpaProperties(jpaProerties());
             return entityManager.getObject();
    }
    
    Properties jpaProerties() {
        Properties jpaProerties = new Properties();
        jpaProerties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        jpaProerties.setProperty("hibernate.show_SQL", "true");
        jpaProerties.setProperty("hibernate.hbm2ddl.auto", "update");
        return jpaProerties;
    }
    
}

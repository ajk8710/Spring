package com.synergisticit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.synergisticit.domain.Employee;
import com.synergisticit.domain.User;

import jakarta.persistence.EntityManagerFactory;

// @ImportResource("classpath:beans2.xml")  // import dataSource bean from beans.xml
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
        
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("driver"));
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setUsername(env.getProperty("myusername"));
        dataSource.setPassword(env.getProperty("mypassword"));
        return dataSource;
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
    
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
//        return transactionManager;
//    }
    
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
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
            LocalContainerEntityManagerFactoryBean entityManagerFactory = new  LocalContainerEntityManagerFactoryBean();
            entityManagerFactory.setDataSource(dataSource());
            entityManagerFactory.setPackagesToScan("com.synergisticit.domain");
            entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            entityManagerFactory.setJpaProperties(jpaProerties());
            return entityManagerFactory;
    }
    
    Properties jpaProerties() {
        Properties jpaProerties = new Properties();
        jpaProerties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        jpaProerties.setProperty("hibernate.show_SQL", "true");
        jpaProerties.setProperty("hibernate.hbm2ddl.auto", "update");
        return jpaProerties;
    }
    
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setAnnotatedClasses(Employee.class, User.class);
//        sessionFactory.setPackagesToScan("com.synergisticit.domain");
//        sessionFactory.setHibernateProperties(jpaProerties());
//        return sessionFactory;
//    }
    
}

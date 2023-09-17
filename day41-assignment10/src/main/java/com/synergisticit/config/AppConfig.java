package com.synergisticit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.synergisticit.domain.User;

@PropertySource(value="classpath:db.properties")
@Configuration
public class AppConfig {

    @Autowired Environment env;  // env so I can get properties
    
    @Bean
    public DataSource dataSource() {  // set up data source with properties defined in db.properties
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("driver"));
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setUsername(env.getProperty("myusername"));
        dataSource.setPassword(env.getProperty("mypassword"));
        return dataSource;
    }
    
    Properties jpaProperties() {  // set up hibernate properties
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        jpaProperties.setProperty("hibernate.show_SQL", "true");
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        return jpaProperties;
    }
    
    @Bean  // Bean must be named entityManagerFactory
    public LocalSessionFactoryBean entityManagerFactory() {
        LocalSessionFactoryBean entityManagerFactory = new LocalSessionFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setAnnotatedClasses(User.class);
        entityManagerFactory.setPackagesToScan("com.synergisticit.domain");
        entityManagerFactory.setHibernateProperties(jpaProperties());
        return entityManagerFactory;
    }
}

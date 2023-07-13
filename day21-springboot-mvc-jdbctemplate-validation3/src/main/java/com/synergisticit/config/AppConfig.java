package com.synergisticit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

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
    
    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }
    
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");  // directory of jsp file
        viewResolver.setSuffix(".jsp");  // extension of jsp file
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("WEB-INF/message/messages");
        return messageSource;
    }
    
    Properties jpaProerties() {
        Properties jpaProerties = new Properties();
        jpaProerties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        jpaProerties.setProperty("hibernate.show_SQL", "true");
        jpaProerties.setProperty("hibernate.hbm2ddl.auto", "update");
        return jpaProerties;
    }
    
    @Bean
    public LocalSessionFactoryBean entityManagerFactory() {
        LocalSessionFactoryBean entityManagerFactory = new LocalSessionFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setAnnotatedClasses(User.class);
        entityManagerFactory.setPackagesToScan("com.synergisticit.domain");
        entityManagerFactory.setHibernateProperties(jpaProerties());
        return entityManagerFactory;
    }
    
}

package com.synergisticit.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

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

}

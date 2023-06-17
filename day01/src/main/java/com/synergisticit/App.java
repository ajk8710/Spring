package com.synergisticit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {
	
    public static void main(String[] args) {
        
        /* Spring Version 6.0.10
         * 
         * spring-core: Basic building block for Spring that in conjunction with Spring Beans provides dependency injection and IoC features
         * spring-core consists: spring-core + spring-jcl
         * 
         * spring-beans provides the configuration framework and basic functionality to instantiate, configure, and assemble java objects 
         * spring-beans consists all of spring-core + spring-beans
         * 
         * spring-context: all of spring-beans + sparing-aop + spring-expression + spring-context
        */
        
        // Central interface to provide configuration for an application.
        ApplicationContext context = new ClassPathXmlApplicationContext();
    }
    
}

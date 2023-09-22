package com.synergisticit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
// @Order(value = 2)
public class ThirdAspect implements Ordered {  // there is no sequence in which aspect runs first
    
    // any modifier, full_name, EmployeeSErvice1,2,3,Empl, all method, any argument
    // After method ends
    @After(value = "execution(* com.synergisticit.service.EmployeeService*.*(..))")  // fully qualified name of method to execute
    public void after(JoinPoint jp) {
        System.out.println("3rd Aspect @After: " + jp.getSignature());
        System.out.println("3rd Aspect @After: " + jp.getSignature().getDeclaringTypeName() + " " + jp.getSignature().getName());
    }
    
    // Before method starts
    @Before(value = "execution(* com.synergisticit.service.EmployeeService*.*(..))")  // fully qualified name of method to execute
    public void before(JoinPoint jp) {
        System.out.println("3nd Aspect @Before: " + jp.getSignature());
        System.out.println("3nd Aspect @Before: " + jp.getSignature().getDeclaringTypeName() + " " + jp.getSignature().getName());
    }

    @Override
    public int getOrder() {
        return 2;
    }

}

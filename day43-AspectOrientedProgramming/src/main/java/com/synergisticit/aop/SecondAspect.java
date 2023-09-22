package com.synergisticit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(value = 1)  // @Before the first, @After the last
public class SecondAspect {  // there is no sequence in which aspect runs first
    
    // any modifier, full_name, EmployeeSErvice1,2,3,Empl, all method, any argument
    // After method ends
    @After(value = "execution(* com.synergisticit.service.EmployeeService*.*(..))")  // fully qualified name of method to execute
    public void after(JoinPoint jp) {
        System.out.println("2nd Aspect @After: " + jp.getSignature());
        System.out.println("2nd Aspect @After: " + jp.getSignature().getDeclaringTypeName() + " " + jp.getSignature().getName());
    }
    
    // Before method starts
    @Before(value = "execution(* com.synergisticit.service.EmployeeService*.*(..))")  // fully qualified name of method to execute
    public void before(JoinPoint jp) {
        System.out.println("2nd Aspect @Before: " + jp.getSignature());
        System.out.println("2nd Aspect @Before: " + jp.getSignature().getDeclaringTypeName() + " " + jp.getSignature().getName());
    }

}

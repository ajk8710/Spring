package com.synergisticit.aop;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.synergisticit.domain.Employee;

/*
 * AOP: A programming paradigm that increases the modularity by allowing the separation of "cross-cutting concern."
 * "Cross-Cutting Concern" is the repeating codes spread throughout the application.
 * 
 * Aspect: A class annotated with @Aspect. It has advises which is the methods annotated with either of
 *         @Before, @After, @AfterReturning, @Around, @AfterThrowing.
 * Aspect: Module of codes for cross-cutting concern, such as logging, security, transaction management.
 * 
 * Advice: is method/action that should be executed on certain conditions (annotations).
 * Advice types: @Before, @After, @AfterReturning, @Around, @AfterThrowing.
 * 
 * JointPoint: When to apply the codes during program execution.
 * PointCut: A predicate expression for where advice should be applied.
 * 
 * Weaving: Connecting aspects to target objects to create and advise object.
 * Types of Weaving: 1. Compile-time (not supported by Spring AOP, but supported by AspectJ).
 *                   2. Run-time (supported by both Spring AOP and AspectJ).
 * 
 * Comparison of Spring AOP and AspectJ:
 * 1. AOP only supports method level join-points.
 * 2. AOP supports run-time weaving and is slower.
 * 
 * 1. AspectJ supports method level, constructor, and field level join-points.
 * 2. AspectJ supports run-time, compile-time, and load-time weaving and is faster.
 */

@Component
@Aspect
@Order(value = 3)
public class FirstAspect {
    
    // any modifier, full_name, EmployeeSErvice1,2,3,Empl, all method, any argument
    // After method ends
    @After(value = "execution(* com.synergisticit.service.EmployeeService*.*(..))")  // fully qualified name of method to execute
    public void after(JoinPoint jp) {
        System.out.println("@After: " + jp.getSignature());
        System.out.println("@After: " + jp.getSignature().getDeclaringTypeName() + " " + jp.getSignature().getName());
    }
    
    // Before method starts
    @Before(value = "execution(* com.synergisticit.service.EmployeeService*.*(..))")  // fully qualified name of method to execute
    public void before(JoinPoint jp) {
        System.out.println("@Before: " + jp.getSignature());
        System.out.println("@Before: " + jp.getSignature().getDeclaringTypeName() + " " + jp.getSignature().getName());
    }
    
    // Join Point is where method starts and ends
    // @Around is in between @Before and @After
    @Around(value = "execution(* com.synergisticit.service.EmployeeService*.*(..))")  // fully qualified name of method to execute
    public Object around(ProceedingJoinPoint pjp) {
        
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        StringBuilder stringBuilder = new StringBuilder();
        Object returnedObj = null;
        try {
            returnedObj = pjp.proceed();
            stopWatch.stop();
            stringBuilder.append(pjp.getTarget().getClass().getName());
            stringBuilder.append(".");
            stringBuilder.append(pjp.getSignature().getName() + "()");
            stringBuilder.append("@Around...Execution Time: " + stopWatch.getTotalTimeSeconds() + "seconds.");
            System.out.println(stringBuilder);
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
        return returnedObj;
    }
    
    // After method returns something
    @AfterReturning(value = "execution(* com.synergisticit.service.EmployeeService*.*(..))", returning = "result")
    public void afterReturning(JoinPoint jp, List<Employee> result) {  // result is variable name
        System.out.println("@AfterReturning: " + result);
        if (result != null) {
            for (Employee e : result) {
                System.out.println(e.getName());
            }
        }
    }
    
    // After method throws exception
    @AfterThrowing(value = "execution(* com.synergisticit.service.EmployeeService*.*(..))", throwing = "e")
    public void afterThrowing(JoinPoint jp, Throwable e) {  // e is variable name
        System.out.println("@AfterThrowing: " + jp.getSignature());
        System.out.println(e.getClass().getSimpleName());  // name of execption class
    }
    
}

package com.synergisticit;

// use of static import to import static method of a class
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.synergisticit.domain.User;
import com.synergisticit.repository.EmployeeRepository;
import com.synergisticit.service.EmployeeService;
import com.synergisticit.service.UserService;

@SpringBootTest
class SpringbootApplicationTests {
    
    /*
     * JUnit is a test framework developed by Erich Gamma and Kent Beck.
     * It is used to test the individual units, i.e. class and their methods.
     * Higher the number of successful test cases, more the quality of software.
     * Developers do the unit testing.
     * 
     * Unit Testing -> Integration Testing -> System Testing -> Acceptance Testing
     * 
     * There is no guarantee in which order the JUnit test cases are executed.
     * JUnit Jupiter is the API for writing tests using JUnit 5.
     * 
     * JUnit 5: JUnit Platform + Jupiter Engine (JUnit 5 related annotations) + Vintage Engine (refers to earlier versions of JUit)
     * All the methods are annotated with @Test representing a test case
     * All the test method returns void.
     * Test method should not be private.
     */
    
    @Autowired EmployeeRepository employeeRepository;
    @Autowired EmployeeService employeeService;
    @Autowired UserService usrService;
    
	@Test
	void contextLoads() {
	    System.out.println("Employee Repository: " + employeeRepository);
	    System.out.println("Employee Service: " + employeeService);
	    //assertNotNull(object to assert, message if fails)
	    assertNotNull(employeeRepository, "employeeRepository is null");
	    assertNotNull(employeeService, "employeeService is null");
	}
	
	@Test
	void testForObjectReference() {
	    System.out.println("testForObjectReference...");
	    String str1 = "USA";
	    String str2 = str1;
	    String str3 = "UK";
	    String str4 = "USA";
	    assertSame(str1, str2);
	    // assertNotSame(str1, str2, "str1 and str2 are actually the same");  // fail
	    assertNotSame(str1, str3);
	    assertSame(str1, str4, "There are not same");  // refers same object as String is reused, gets from existing pool
	}
	
	@Test
	void testExpectingException() {
	    System.out.println("testExpectingException...");
	    assertThrows(NumberFormatException.class, () -> Integer.parseInt("Hundred"), "execption is not thrown");
	}
	
    @Test
    void testExpectingException2() {
        System.out.println("testExpectingException2...");
        // assertThrows(NumberFormatException.class, () -> Integer.parseInt("100"), "execption is not thrown");  // fail
    }
    
    @Test
    void testExpectingException3() {
        System.out.println("testExpectingException3...");
        // assertThrows(IllegalArgumentException.class, () -> Integer.parseInt("100"));  // fail
        assertThrows(IllegalArgumentException.class, () -> Integer.parseInt("Hundred"));
    }
    
    @Test
    void testExpectingException4() {
        System.out.println("testExpectingException4...");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> Integer.parseInt("Hundred"));
        assertNotEquals("Argument must be number", ex.getMessage(), "Argument must be number");
        System.out.println(ex.getMessage()); // message is: For input string: "Hundred"
    }
    
    @Disabled  // ignores a particular test, skipped
    @Test
    void timeoutExceeded1() {
        System.out.println("timeoutExceeded1...");
        // assertTimeout(Duration.ofMillis(10), () -> Thread.sleep(11));  // fail
        // assertTimeout(Duration.ofMillis(10), () -> Thread.sleep(10)); // fail
        assertTimeout(Duration.ofMillis(10), () -> Thread.sleep(9));
    }
    
    @Test  // tests case with private method is not executed
    private void timeoutExceeded2() {
        System.out.println("timeoutExceeded2...");
        assertTimeout(Duration.ofMillis(10), () -> Thread.sleep(9));
    }
    
    @Test
    protected void timeoutExceeded3() {
        System.out.println("timeoutExceeded3...");
        assertTimeout(Duration.ofMillis(10), () -> Thread.sleep(5));
    }
    
    @Test  // test case that returns anything other than void is not executed
    int timeoutExceeded4() {
        System.out.println("timeoutExceeded4...");
        assertTimeout(Duration.ofMillis(10), () -> Thread.sleep(5));
        return 0;
    }

    @BeforeEach
    public void beforeTheExecutionOfEachTestCase() {
        System.out.println("before TheExecutionOfEachTestCase...");
    }
    
    @AfterEach
    public void afterTheExecutionOfEachTestCase() {
        System.out.println("after TheExecutionOfEachTestCase...");
    }
    
    @BeforeAll  // before all and after all should be static methods
    public static void beforeStarting() {
        System.out.println("before starting...");
    }
    
    @AfterAll  // mostly used for clean up and to close resources
    public static void afterAll() {
        System.out.println("afterAll...");
    }
    
    @Test
    public void getUser() {
        System.out.println("User Service Test...");
        User user = usrService.findById(1);
        String username = user.getUsername();
        System.out.println("User Name: " + username);
        assertEquals("Potato", username, "Actual Username is : " + username);
    }
}

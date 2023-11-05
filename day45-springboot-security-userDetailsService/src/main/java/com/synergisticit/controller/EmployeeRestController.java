package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Employee;
import com.synergisticit.service.EmployeeService;

import jakarta.validation.Valid;

@RestController  // @Controller + @ResponseBody
public class EmployeeRestController {
    
    /*
     * REST: Representational State Transfer is an architectural style
     * or principal to be followed while designing/doing the project.
     * 
     * Resource: Anything on the server is resource. The resources should be accessed uniformly using the HTTP methods.
     * 
     * Stateless Architecture:
     * We don't use HttpSession, Cookies etc. HTTP Status Code and HTTP Headers are used to communicate between server and client.
     * 
     * Oracle provides JAX-RS sepcification to create restful web services.
     * Some implementations of this specification are Xerces, Apache CFX.
     * JAX-WS is the Java API for XML based web services. The name JAX-RS was derived in analogy to JAX-WS.
     * Spring provides the implementation of JAX-RS over its MVC framework
     * 
     * HTTP methods to be used here: GET, POST, PUT, DELETE
     * Usage: GET=select, POST=create, PUT=update, DELTE=delete
     * 
     * Useful headers:
     * Accept: Media type acceptable to response, it corresponds to "Produces"
     * Content Type: The media type of the body of the Request.
     * Authorization header.
     * 
     * Http Request Methods: 1)GET  2)HEAD  3)POST  4)PUST  5)DELETE  6)OPTIONS  7)TRACE  8)CONNECT   9)PATCG
     * 
     */

    @Autowired EmployeeService employeeService;
    
    // When client requests this url and request method is GET, run this method.
    // Entering web address into the browser is GET. (The browser will send a GET request.)
    // http://localhost:8080/employee?empId=1
    // @RequestMapping("employee")  // if HTTP method is not mentioned, by default, it is HTTP GET
    // @RequestMapping(value="employee", method=RequestMethod.GET)
    // @GetMapping(value="employee")
    @RequestMapping(value= {"e", "employee"}, method=RequestMethod.GET)
    public ResponseEntity<Employee> getEmpleeById(@RequestParam int empId) {
        
        Employee employee = employeeService.findById(empId);
        return new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
    }
    
    // http://localhost:8080/employee/all
    @RequestMapping(value={"employee/all", "employees"}, method=RequestMethod.GET)
    // @GetMapping("emps")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        
        List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.FOUND);
    }
    
    // When client requests this url and request method is DELETE, run this method.
    // http://localhost:8080/employee/delete?empId=2
    @RequestMapping(value="employee/delete", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmployeeById(@RequestParam int empId) {
        Employee e = employeeService.findById(empId);
        if (e != null) {
            employeeService.deleteById(empId);
            return new ResponseEntity<String>("Employee deleted for id=" + empId, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("Employee not found for id=" + empId, HttpStatus.NOT_FOUND);
        }
    }
    
    // When client requests this url and request method is PUT, run this method.
    // http://localhost:8080/employee/update?empId=1
    // Send JSON (Request Body) on Postman App:
    /*
     * {
     *     "empId": 1,
     *     "name": "Potato",
     *     "designation": "Food",
     *     "salary": 120000
     * }
     */
    @RequestMapping(value="employee/update", method=RequestMethod.PUT)
    public ResponseEntity<?> updateEmployeeById(@RequestParam int empId, @RequestBody Employee employee) {
        Employee e = employeeService.findById(empId);
        if (e != null) {
            employeeService.save(employee);  // saves what's passed in as request body
            return new ResponseEntity<String>("Employee updated for id=" + empId, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("Employee not found for id=" + empId, HttpStatus.NOT_FOUND);
        }
    }
    
    // http://localhost:8080/employee/create
    // Send JSON or XML (Request Body) on Postman App:
    /*
     * {
     *     "empId": 1,
     *     "name": "Potato",
     *     "designation": "Food",
     *     "salary": 120000
     * }
     * 
     * <Employee>
     * <empId>9</empId>
     * <name>Turkey</name>
     * <designation>Meat</designation>
     * <salary>90000</salary>
     * </Employee>
     */
    // @RequestMapping(value="employee/create", method=RequestMethod.POST) // goes for default JSON supported by Spring Boot
    // Content-Type headers from client must be JSON, Accept header should be JSON (or can be xml and still works on Postman)
    
    // consumes Content-Type from client. What it produces must match what client Accepts
    // @RequestMapping(value="employee/create", method=RequestMethod.POST, consumes="application/json", produces="application/xml")
    // @RequestMapping(value="employee/create", method=RequestMethod.POST, consumes="application/xml", produces="application/json")
    // @RequestMapping(value="employee/create", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_XML_VALUE)
    @PostMapping(value="employee/create", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee, BindingResult br) {
        int eid = employee.getEmpId();
        if (employeeService.existsById(eid)) {
            return new ResponseEntity<String>("Employee already exists for id=" + eid, HttpStatus.FOUND);
        }
        else if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = br.getFieldErrors();
            for (FieldError f : fieldErrors) {
                sb = sb.append(f.getField() + ": ").append(f.getDefaultMessage() + "\n");
            }
            return new ResponseEntity<String>("Invalid input for following properties:\n" + sb, HttpStatus.ACCEPTED);
        }
        else {
            employeeService.save(employee);
            return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
        }
    }
    
}

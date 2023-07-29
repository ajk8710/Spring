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

import com.synergisticit.domain.User;
import com.synergisticit.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserRestController {
    
    @Autowired UserService userService;
    
    // http://localhost:8080/user?userId=1
    @RequestMapping(value="user", method=RequestMethod.GET)
    // @RequestMapping(value="user", method=RequestMethod.GET, produces="application/xml")
    // @GetMapping(value="user", produces=MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getUserById(@RequestParam long userId) {
        User user = userService.findUserById(userId);
        if (user == null) {
            return new ResponseEntity<String>("User not found with id=" + userId, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<User>(user, HttpStatus.FOUND);
        }
    }
    
    // http://localhost:8080/user/all
    @RequestMapping(value={"user/all", "users"}, method=RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.FOUND);
    }
    
    // http://localhost:8080/user/create - save if id not already exist
    @RequestMapping(value="user/create", method=RequestMethod.POST)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult br) {
        long userId = user.getUserId();
        if (userService.existByUserId(userId)) {
            return new ResponseEntity<String>("User already exists with id=" + userId, HttpStatus.FOUND);
        }
        else if (br.hasFieldErrors()) {
            String errorMessage = "Invalid input for following properties:\n";
            List<FieldError> fieldErrors = br.getFieldErrors();
            for (FieldError f : fieldErrors) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
        }
        else {
            userService.saveUser(user);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }
    }
    
    // http://localhost:8080/user/update - save if id already exists
    @RequestMapping(value="user/update", method=RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user, BindingResult br) {
        long userId = user.getUserId();
        if (!userService.existByUserId(userId)) {
            return new ResponseEntity<String>("User does not exist with id=" + userId, HttpStatus.NOT_FOUND);
        }
        else if (br.hasFieldErrors()) {
            String errorMessage = "Invalid input for following properties:\n";
            List<FieldError> fieldErrors = br.getFieldErrors();
            for (FieldError f : fieldErrors) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
        }
        else {
            userService.saveUser(user);
            return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
        }
    }
    
    // http://localhost:8080/user/delete?userId=10
    @RequestMapping(value="user/delete", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteUserById(@RequestParam long userId) {
        if (userService.existByUserId(userId)) {
            userService.deleteUserById(userId);
            return new ResponseEntity<String>("User deleted with id=" + userId, HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<String>("User does not exists with id=" + userId, HttpStatus.NOT_FOUND);
        }
    }
    
}

/*
 * 
 * {
 * "userId": 3,
 * "username": "Potato",
 * "password": "123",
 * "email": "po@po.com",
 * "mobile": "1111111111",
 * "roles": [ {"roleId": 3, "name": "Specialist" } ]
 * }
 * 
*/

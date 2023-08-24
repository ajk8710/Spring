package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.User;
import com.synergisticit.service.UserService;
import com.synergisticit.validation.UserValidator;

import jakarta.validation.Valid;

@RestController
public class UserRestController {
    
    @Autowired UserService userService;
    @Autowired UserValidator userValidator;
    
    // http://localhost:8080/user?userId=1
    @RequestMapping(value="user", method=RequestMethod.GET)
    public ResponseEntity<?> getUserById(@RequestParam Long userId) {
        User user = userService.getUserById(userId);
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
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.FOUND);
    }
    
    // http://localhost:8080/user/create - save if id not already exist
    /*
{
    "userId": 10,
    "username": "Son",
    "password": "123",
    "roles": [
        {
            "roleId": 1,
            "roleName": "Admin" - not needed, it only considers roleId to add entity to the table user_role
        }
    ]
}
     */
    // Delete @JsonManagedReference on User.java otherwise it throws "application/json;charset=UTF-8' is not supported" error on create method
    @RequestMapping(value="user/create", method=RequestMethod.POST)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult br) {
        userValidator.validate(user, br);
        
        Long userId = user.getUserId();
        if (userService.existById(userId)) {
            return new ResponseEntity<String>("User already exists with id=" + userId, HttpStatus.FOUND);
        }
        else if (br.hasFieldErrors()) {
            String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
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
        userValidator.validate(user, br);
        
        Long userId = user.getUserId();
        if (!userService.existById(userId)) {
            return new ResponseEntity<String>("User does not exist with id=" + userId, HttpStatus.NOT_FOUND);
        }
        else if (br.hasFieldErrors()) {
            String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
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
    public ResponseEntity<String> deleteUserById(@RequestParam Long userId) {
        if (userService.existById(userId)) {
            userService.deleteUserById(userId);
            return new ResponseEntity<String>("User deleted with id=" + userId, HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<String>("User does not exist with id=" + userId, HttpStatus.NOT_FOUND);
        }
    }
    
}

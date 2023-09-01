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

import jakarta.validation.Valid;

@RestController
public class UserRestController {
    
    @Autowired UserService userService;
    
    // http://localhost:8080/user?userid=1
    @RequestMapping(value="user", method=RequestMethod.GET)
    public ResponseEntity<?> findUserById(@RequestParam Long userid) {
        User user = userService.findById(userid);
        if (user == null) {
            return new ResponseEntity<String>("User not found with id=" + userid, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<User>(user, HttpStatus.FOUND);
        }
    }
    
    // http://localhost:8080/user/all
    @RequestMapping(value={"user/all", "users"}, method=RequestMethod.GET)
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.FOUND);
    }

    /*
{
    "userid": 1,
    "username": "Potato",
    "dob": "2008-01-01",
    "email": "po@po.com"
}
     */
    // http://localhost:8080/user/save
    @RequestMapping(value="user/save", method=RequestMethod.POST)
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user, BindingResult br) {
        
        if (br.hasFieldErrors()) {
            String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
        }
        else {
            userService.save(user);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }
    }
    
    // http://localhost:8080/user/update - save if id already exists
    @RequestMapping(value="user/update", method=RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user, BindingResult br) {
        
        Long userid = user.getUserid();
        if (userService.findById(userid) == null) {
            return new ResponseEntity<String>("User does not exist with id=" + userid, HttpStatus.NOT_FOUND);
        }
        else if (br.hasFieldErrors()) {
            String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
        }
        else {
            userService.save(user);
            return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
        }
    }
    
    // http://localhost:8080/user/delete?userid=10
    @RequestMapping(value="user/delete", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteUserById(@RequestParam Long userid) {
        if (userService.findById(userid) != null) {
            userService.deleteById(userid);
            return new ResponseEntity<String>("User deleted with id=" + userid, HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<String>("User does not exist with id=" + userid, HttpStatus.NOT_FOUND);
        }
    }
    
}

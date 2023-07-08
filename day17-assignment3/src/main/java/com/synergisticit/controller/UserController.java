package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.User;
import com.synergisticit.service.UserService;

@Controller
public class UserController {
    
    @Autowired UserService userService;  // autowiring a bean that implements UserService interface
    
    @RequestMapping(value="userForm")  // @RequestMapping maps url to method
    public String userForm(User user, Model model) {  // User required since it is used in jsp file
        model.addAttribute("users", userService.findAll());
        return "userForm";  // view is jsp file name (with help of utilizing ViewResolver)
    }
    
    @RequestMapping(value="saveUser")
    public String saveUser(User user) {
        userService.save(user);
        return "userForm";
    }
    
}

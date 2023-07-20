package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.User;
import com.synergisticit.service.UserService;

@Controller
public class UserController {
    
    @Autowired UserService userService;
    
    @RequestMapping("userForm")
    public String userForm(User user, Model model) {
        modelData(model);
        return "userForm";
    }
    
    @RequestMapping("saveUser")
    public String saveUser(User user, Model model) {
        userService.save(user);
        
        modelData(model);
        return "userForm";
    }
    
    private void modelData(Model model) {
        model.addAttribute("users", userService.findAll());
    }
}

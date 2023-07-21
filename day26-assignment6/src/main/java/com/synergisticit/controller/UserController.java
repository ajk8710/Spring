package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.User;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;

@Controller
public class UserController {
    
    @Autowired UserService userService;
    @Autowired RoleService roleService;
    
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
    
    @RequestMapping("updateUser")
    public String updateUser(User user, Model model) {  // User object created with only userId is passed in by request param
        User retrievedUser = userService.findById(user.getUserId());
        model.addAttribute("user", retrievedUser);  // user form is filled in by path
        
        modelData(model);
        return "userForm";
    }
    
    @RequestMapping("deleteUser")
    public String deleteUser(User user, Model model) {
        userService.deleteById(user.getUserId());
        
        modelData(model);
        return "userForm";
    }
    
    private void modelData(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("ListofAllRoles", roleService.findAll());
    }
}

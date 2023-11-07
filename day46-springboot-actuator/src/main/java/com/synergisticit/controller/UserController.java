package com.synergisticit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {
    
    @Autowired UserService userService;
    @Autowired RoleService roleService;
    
    @PreAuthorize("hasAuthority('Admin')")
    @RequestMapping("userForm")
    public String userForm(User user, Model model) {
        modelData(model);
        
        // Retrieving current logged-in user info
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Credentials: " + auth.getCredentials());  // prints null. password removed for security as soon as logged-in.
        System.out.println("Authorities: " + auth.getAuthorities());
        System.out.println("Details: " + auth.getDetails());  // prints sessionId. If sessionId is the same, user is not required to log in.
        System.out.println("Name: " + auth.getName());
        System.out.println("Principal: " + auth.getPrincipal());
        
        return "userForm";
    }
    
    @PreAuthorize("hasAuthority('Admin')")
    @RequestMapping("saveUser")
    public String userForm(@Valid @ModelAttribute User user, BindingResult br, Model model) {
        if (!br.hasErrors()) {
            userService.save(user);
            return "redirect:userForm";
        }
        
        modelData(model);
        return "userForm";
    }
    
    @RequestMapping("updateUser")
    public String updateUser(User user, Model model) {  // User object created with only userId is passed in by request param
        User retrievedUser = userService.findById(user.getUserId());
        
        // Having model.addAttribute("user", retrievedUser) and letting user form to be filled in by path without specifying value
        // led to error due to user.role to be filled in selection form of String - cannot convert Object (Role) to String.
        model.addAttribute("retrievedUser", retrievedUser);
        
        List<Role> selectedRoles = new ArrayList<>(retrievedUser.getRoles());
        model.addAttribute("selectedRoles", selectedRoles);
        
        modelData(model);
        return "userForm";
    }
    
    private void modelData(Model model) {
        model.addAttribute("ListofAllRoles", roleService.findAll());
        model.addAttribute("users", userService.findAll());
    }
}

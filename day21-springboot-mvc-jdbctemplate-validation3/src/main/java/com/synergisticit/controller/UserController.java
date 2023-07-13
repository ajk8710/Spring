package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.User;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;
import com.synergisticit.validation.UserValidator;

import jakarta.validation.Valid;

@Controller
public class UserController {
    
    @Autowired UserService userService;  // autowiring a bean that implements UserService interface
    @Autowired UserValidator userValidator;
    
    @Autowired RoleService roleService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // binder.setValidator(userValidator);
        binder.addValidators(userValidator);
    }
    
    @RequestMapping(value="userForm")  // @RequestMapping maps url to method
    public String userForm(User user, Model model) {  // User as param is required since it is used in jsp file
        model.addAttribute("users", userService.findAll());
        model.addAttribute("availableRoles", roleService.findAll());
        return "userForm";  // view is jsp file name (with help of ViewResolver)
    }
    
    @RequestMapping(value="saveUser")
    public String saveUser(@Valid @ModelAttribute User user, BindingResult br, Model model) {
        // userValidator.validate(user, br);
        
        System.out.println("Saving: " + user);
        System.out.println("br:" + br.hasErrors());
        
        if (! br.hasErrors()) {
            userService.save(user);
        }
        model.addAttribute("users", userService.findAll());
        model.addAttribute("availableRoles", roleService.findAll());
        return "userForm";
    }
    
    @RequestMapping(value="updateUser")
    public String updateUser(@ModelAttribute User user, Model model) {  // user object with userid and no other field passed in through route param
        System.out.println("Updating: " + user);
        User retrievedUser = userService.findById(user.getUserid());  // search for the userid in db
        System.out.println("Updating: " + retrievedUser);
        model.addAttribute("retrievedUser", retrievedUser);
        model.addAttribute("users", userService.findAll());
        model.addAttribute("availableRoles", roleService.findAll());
        return "userForm";
    }
    
    @RequestMapping(value="deleteUser")
    public String deleteUser(@ModelAttribute User user, Model model) {  // user object with userid and no other field passed in through route param
        System.out.println("Deleting: " + user);
        userService.deleteById(user.getUserid());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("availableRoles", roleService.findAll());
        return "userForm";
    }

}

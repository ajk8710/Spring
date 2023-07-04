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
    
    @RequestMapping(value="userForm")
    public String userForm(User user, Model model) {
        model.addAttribute("users", userService.findAll());
        return "userForm";
    }
    
    @RequestMapping(value="saveUser")
    public String saveUser(User user, Model model) {  // jpa repository automatically checks if record already exists
        userService.save(user);
        model.addAttribute("users", userService.findAll());
        return "userForm";
    }
    
    @RequestMapping(value="updateUser")
    public String updateUser(User user, Model model) {
        User retrievedUser = userService.findById(user.getUserid());
        model.addAttribute("retrievedUser", retrievedUser);
        model.addAttribute("users", userService.findAll());
        return "userForm";
    }
    
    @RequestMapping(value="deleteUser")
    public String deleteUser(User user, Model model, long userid) {  // @ModelAttribute tag can be ommited
        System.out.println(userid);  // @RequestParam tag can be ommited
        // if (retrievedUser != null) { - handled by JpaRepository
            userService.deleteById(user.getUserid());
        // }
        model.addAttribute("users", userService.findAll());
        return "userForm";
    }
}

package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Role;
import com.synergisticit.service.RoleService;

import jakarta.validation.Valid;

@Controller
public class RoleController {
    
    @Autowired RoleService roleService;
    
    @RequestMapping("roleForm")
    public String roleForm(Role role, Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "roleForm";
    }
    
    @RequestMapping("saveRole")
    public String saveRole(@Valid @ModelAttribute Role role, BindingResult br, Model model) {
        
        if (! br.hasErrors()) {
            roleService.save(role);
        }
        
        model.addAttribute("roles", roleService.findAll());
        return "roleForm";
    }
    
    @RequestMapping("deleteRole")
    public String deleteRole(@ModelAttribute Role role, Model model) {
        System.out.println("Deleting: " + role);
        roleService.deleteRoleById(role.getRoleId());
        model.addAttribute("roles", roleService.findAll());
        return "roleForm";
    }
    
    @RequestMapping("updateRole")
    public String updateRole(@ModelAttribute Role role, Model model) {
        Role retriedvedRole = roleService.findRoleById(role.getRoleId());
        model.addAttribute("retriedvedRole", retriedvedRole);
        model.addAttribute("roles", roleService.findAll());
        return "roleForm";
    }
}

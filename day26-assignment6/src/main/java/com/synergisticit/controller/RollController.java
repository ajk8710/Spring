package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Role;
import com.synergisticit.service.RoleService;

@Controller
public class RollController {
    
    @Autowired RoleService roleService;
    
    @RequestMapping("roleForm")
    public String roleForm(Role role, Model model) {
        modelData(model);
        return "roleForm";
    }
    
    @RequestMapping("saveRole")
    public String saveRole(Role role, Model model) {
        roleService.save(role);
        
        modelData(model);
        return "roleForm";
    }
    
    @RequestMapping("updateRole")
    public String updateRole(Role role, Model model) {  // Role object created with only roleId is passed in by request param
        Role retrievedRole = roleService.findById(role.getRoleId());
        model.addAttribute("role", retrievedRole);  // role form is filled in by path
        
        modelData(model);
        return "roleForm";
    }
    
    @RequestMapping("deleteRole")
    public String deleteRole(Role role, Model model) {
        roleService.deleteById(role.getRoleId());
        
        modelData(model);
        return "roleForm";
    }
    
    private void modelData(Model model) {
        model.addAttribute("roles", roleService.findAll());
    }
}

package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Role;
import com.synergisticit.service.RoleService;
import com.synergisticit.validation.RoleValidator;

import jakarta.validation.Valid;

@Controller
public class RoleController {
    
    @Autowired RoleService roleService;
    @Autowired RoleValidator roleValidator;
    
    @RequestMapping("roleForm")
    public String roleForm(Role role, Model model) {
        modelData(model);
        return "roleForm";
    }
    
    @RequestMapping("saveRole")
    public String saveRole(@Valid @ModelAttribute Role role, BindingResult br, Model model) {  // BindingResult must come before Model, otherwise Model will send to error page before BindingResult do its job
        roleValidator.validate(role, br);
        
        if (!br.hasErrors()) {
            roleService.saveRole(role);
            // do not need to call modelData(model) because it's in roleForm method
            return "redirect:roleForm";  // redirect to url roleForm which calls roleForm method
        }

        modelData(model);
        return "roleForm";  // do not redirect, keep the info entered and show error messages
    }
    
    @RequestMapping("updateRole")
    public String updateRole(Role role, Model model) {  // Role object created with only roleId is passed in by request param
        Role retrievedRole = roleService.getRoleById(role.getRoleId());
        model.addAttribute("role", retrievedRole);  // role form is filled in by path
        
        modelData(model);
        return "roleForm";
    }
    
    @RequestMapping("deleteRole")
    public String deleteRole(Role role, Model model) {
        roleService.deleteRoleById(role.getRoleId());
        
        modelData(model);
        return "redirect:roleForm";
    }
    
    private void modelData(Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
    }
}

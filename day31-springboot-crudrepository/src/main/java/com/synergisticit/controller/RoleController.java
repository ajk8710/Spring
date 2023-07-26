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

// All the requests/responses have to go through the front controller named DispatcherServlet (provided by Spring framework)
// DispatcherServlet interacts with HandlerMapping to know the correct Controller that will execute the required method to do the job
// - Finding which controller to do the job
@Controller
public class RoleController {
    
    @Autowired RoleService roleService;
    
    @RequestMapping("roleForm")
    public String roleForm(Role role, Model model) {
        modelData(model);
        return "roleForm";
    }
    
    @RequestMapping("saveRole")
    public String saveRole(@Valid @ModelAttribute Role role, BindingResult br, Model model) {
        if (!br.hasErrors()) {
            roleService.save(role);
            return "redirect:roleForm";
        }
        
        modelData(model);
        return "roleForm";
    }
    
    @RequestMapping("updateRole")
    public String updateRole(Role role, Model model) {
        role = roleService.findRoleByID(role.getRoleId());
        model.addAttribute("role", role);  // role form is filled in by path
        
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
        model.addAttribute("roles", roleService.findAll());
    }
}

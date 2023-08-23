package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Role;
import com.synergisticit.service.RoleService;

import jakarta.validation.Valid;

@RestController
public class RoleRestController {
    
    @Autowired RoleService roleService;
    
    // http://localhost:8080/role?roleId=1
    @RequestMapping(value="role", method=RequestMethod.GET)
    public ResponseEntity<?> getRoleById(@RequestParam Long roleId) {
        Role role = roleService.getRoleById(roleId);
        if (role == null) {
            return new ResponseEntity<String>("Role not found with id=" + roleId, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<Role>(role, HttpStatus.FOUND);
        }
    }
    
    // http://localhost:8080/role/all
    @RequestMapping(value={"role/all", "roles"}, method=RequestMethod.GET)
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return new ResponseEntity<List<Role>>(roles, HttpStatus.FOUND);
    }
    
    // http://localhost:8080/role/create - save if id not already exist
    /*
        {
        "roleId": 4,
        "roleName": "Banker"
        }
     */
    @RequestMapping(value="role/create", method=RequestMethod.POST)
    public ResponseEntity<?> createRole(@Valid @RequestBody Role role, BindingResult br) {
        long roleId = role.getRoleId();
        if (roleService.existById(roleId)) {
            return new ResponseEntity<String>("Role already exists with id=" + roleId, HttpStatus.FOUND);
        }
        else if (br.hasFieldErrors()) {
            String errorMessage = "Invalid input for following properties:\n";
            List<FieldError> fieldErrors = br.getFieldErrors();
            for (FieldError f : fieldErrors) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
        }
        else {
            roleService.saveRole(role);
            return new ResponseEntity<Role>(role, HttpStatus.CREATED);
        }
    }
    
    // http://localhost:8080/role/delete?roleId=10
    @RequestMapping(value="role/delete", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteRoleById(@RequestParam long roleId) {
        if (roleService.existById(roleId)) {
            roleService.deleteRoleById(roleId);
            return new ResponseEntity<String>("Role deleted with id=" + roleId, HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<String>("Role does not exist with id=" + roleId, HttpStatus.NOT_FOUND);
        }
    }
}

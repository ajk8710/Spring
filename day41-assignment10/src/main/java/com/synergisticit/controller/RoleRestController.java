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
    public ResponseEntity<?> findRoleById(@RequestParam Long roleId) {
        Role role = roleService.findById(roleId);
        if (role == null) {
            return new ResponseEntity<String>("Role not found with id=" + roleId, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<Role>(role, HttpStatus.FOUND);
        }
    }
    
    // http://localhost:8080/role/all
    @RequestMapping(value={"role/all", "roles"}, method=RequestMethod.GET)
    public ResponseEntity<List<Role>> findAllRoles() {
        List<Role> roles = roleService.findAll();
        return new ResponseEntity<List<Role>>(roles, HttpStatus.FOUND);
    }

    /*
{
    "roleId": 1,
    "roleName": "Admin"
}
    */
    // http://localhost:8080/role/save
    @RequestMapping(value="role/save", method=RequestMethod.POST)
    public ResponseEntity<?> saveRole(@Valid @RequestBody Role role, BindingResult br) {
        
        if (br.hasFieldErrors()) {
            String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
        }
        else {
            roleService.save(role);
            return new ResponseEntity<Role>(role, HttpStatus.CREATED);
        }
    }
    
    // http://localhost:8080/role/update - save if id already exists
    @RequestMapping(value="role/update", method=RequestMethod.PUT)
    public ResponseEntity<?> updateRole(@Valid @RequestBody Role role, BindingResult br) {
        
        Long roleId = role.getRoleId();
        if (roleService.findById(roleId) == null) {
            return new ResponseEntity<String>("Role does not exist with id=" + roleId, HttpStatus.NOT_FOUND);
        }
        else if (br.hasFieldErrors()) {
            String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
        }
        else {
            roleService.save(role);
            return new ResponseEntity<Role>(role, HttpStatus.ACCEPTED);
        }
    }
    
    // http://localhost:8080/role/delete?roleId=10
    @RequestMapping(value="role/delete", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteRoleById(@RequestParam Long roleId) {
        if (roleService.findById(roleId) != null) {
            roleService.deleteById(roleId);
            return new ResponseEntity<String>("Role deleted with id=" + roleId, HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<String>("Role does not exist with id=" + roleId, HttpStatus.NOT_FOUND);
        }
    }
    
}

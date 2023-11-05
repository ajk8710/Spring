package com.synergisticit.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    
    @Autowired UserService userService;
    @Autowired RoleService roleService;
    
    @RequestMapping(value={"home", "/"})
    public String home2() {
        return "home";
    }
    
    @RequestMapping("home2")
    public ResponseEntity<String> home() {
        StringBuilder sb = new StringBuilder();
        sb.append("<div align=\"center\">")
        .append("<br><a href=\"/register\">Register Here</a> | ")
        .append("<a href=\"/home\">Home</a> | ")
        .append("<a href=\"/employeeForm\">Employee Form</a> | ")
        .append("<a href=\"/userForm\">User Form</a> | ")
        .append("<a href=\"/roleForm\">Role Form</a> | ")
        .append("<a href=\"/login\">Log In</a>");
        return new ResponseEntity<String>(sb.toString(), HttpStatus.OK);
    }
    
    @RequestMapping("register")
    public ResponseEntity<String> register() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("<form action=\"registerUser\">")
        .append("<div align=\"center\"/>")
        .append("<table>")

        .append("    <tr>")
        .append("    <td> <b>ID:</b> </td>")
        .append("    <td> <input name=\"userId\"/> </td>")
        .append("    </tr>")

        .append("    <tr>")
        .append("    <td> <b>Name:</b> </td>")
        .append("    <td> <input name=\"username\"/> </td>")
        .append("    </tr>")

        .append("    <tr>")
        .append("    <td> <b>Password:</b> </td>")
        .append("    <td> <input name=\"password\"/> </td>")
        .append("    </tr>")

        .append("    <tr>")
        .append("    <td> <b>Email:</b> </td>")
        .append("   <td> <input name=\"email\"/> </td>")
        .append("    </tr>")

        .append("    <tr>")
        .append("    <td> <b>Mobile:</b> </td>")
        .append("    <td> <input name=\"mobile\"/> </td>")
        .append("    </tr>")

        .append("    <tr>")
        .append("    <td colspan=\"2\" align=\"center\"> <input type=\"submit\" value=\"Submit\"/> </td>")
        .append("    </tr>")

        .append("</table>")
        .append("</form>")
        .append("</div>");

        return new ResponseEntity<String>(sb.toString(), HttpStatus.OK);
    }
    
    @RequestMapping("registerUser")
    public ResponseEntity<String> registerUser(@RequestParam String userId, @RequestParam String username,
            @RequestParam String password, @RequestParam String email, @RequestParam String mobile) {
        
        StringBuilder sb = new StringBuilder();
        
        Long longId = Long.valueOf(userId);
        if (!userService.existById(longId)) {
            Role userRole = roleService.findRoleByID(3L);  // assign user role
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(userRole);
            User user = new User(longId, username, password, email, mobile, roleSet);
            
            User savedUser = userService.save(user);
            sb.append(savedUser);
        }
        else {
            sb.append("User with id=" + longId + " already exists.")
            .append("<br/><a href=\"/home\">Home</a>");
        }
        
        return new ResponseEntity<String>(sb.toString(), HttpStatus.OK);
    }
    
//    @RequestMapping("login")
//    public String login() {
//        System.out.println("I'm in login");
//        return "login";
//    }
    
    @RequestMapping("login")
    public String login(@RequestParam(required = false) String logout,
            @RequestParam(required = false) String error,
            HttpServletRequest req, HttpServletResponse res, Model model) {
        System.out.println("I'm in login");
        
        String messageAfterLogInOut = null;
        if (logout != null) {  // logout is empty string, not null, if parameter has passed (login?logout)  
            // Following commented lines can also done by SecurityConfig .logout() - invalidate session & clear auth
            // Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
            // if (auth != null) {
            // new SecurityContextLogoutHandler().logout(req, res, auth);
            System.out.println("Log out called");
                messageAfterLogInOut = "You have been logged out";
            // }
        }
        
        if (error != null) {  // error is empty string, not null, if parameter has passed (login?error)  
            messageAfterLogInOut = "Either username or passowrd is not correct.";
        }
        
        model.addAttribute("messageAfterLogInOut", messageAfterLogInOut);
        // return login.jsp if login failed. If success, go to home using SecurityConfig: .defaultSuccessUrl("/home", true)
        return "login";
    }

    @RequestMapping("accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
    
}

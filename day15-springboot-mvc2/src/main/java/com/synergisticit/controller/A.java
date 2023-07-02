package com.synergisticit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// MVC = Model + View + Controller
// FonrtController: DispatcherServlet
// All the request and response have to pass through the FonrtController (i.e. DispatcherServlet)
@Controller
public class A {
    
    @RequestMapping("a")  // mapping should be unique, otherwise spring doesn't know which method to call
    public void m1() {
        System.out.println("------m1()------");
    }
    
    @RequestMapping("b")
    public void m2() {
        System.out.println("------m2()------");
    }
    
    @RequestMapping("x/y/z")
    public void m3() {
        System.out.println("------m3()------");
    }
    
}

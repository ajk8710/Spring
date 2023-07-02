package com.synergisticit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class B {
    
    @RequestMapping("b1")
    public void b1() {
        System.out.println("------b1()------");
    }
    
    @RequestMapping("a2")  // mapping should be unique, otherwise spring doesn't know which method to call
    public void a() {
        System.out.println("------a()------");
    }
    
    
}

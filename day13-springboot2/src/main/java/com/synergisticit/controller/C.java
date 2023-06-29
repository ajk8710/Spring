package com.synergisticit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// @RequestMapping("c")  // this takes over any other mapping inside the class
public class C {
    
    @RequestMapping("c1")  // Due to class-level request mapping, this is hidden 
    public void c1() {
        System.out.println("------c1()------");
    }
    
    @RequestMapping("c2")
    public ResponseEntity<String> c2() {
        String str = "<h1><strong>------c2()------</strong></h1><br>"
                + "------c2()------<br>"
                + "------c2()------<br>";
        System.out.println(str);
        return new ResponseEntity<String>(str, HttpStatus.OK);
    }

}

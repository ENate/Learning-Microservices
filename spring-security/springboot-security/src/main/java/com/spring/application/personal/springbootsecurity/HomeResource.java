package com.spring.application.personal.springbootsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource{
    
    @GetMapping("/")
    public String home(){
        return ("<h1> Welcome to the home page!. </h1>");
    }
    
    // User
    @GetMapping("/user")
    public String user(){
        return ("<h1> Welcome to the user page! </h1>");
    }
    
    // Admin
    @GetMapping("/admin")
    public String admin(){
        return ("<h1> Welcome to the admin page!. </h1>");
    }
    
}
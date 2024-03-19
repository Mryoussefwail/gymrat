package com.example.jimrat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class LoginPageController {
    private LoginController loginController;
    LoginPageController(LoginController loginController){
        this.loginController =loginController;
    }
    @GetMapping("/login")
    public String getLoginPage(){
        return "login.html";
    }

}

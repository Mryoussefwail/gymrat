package com.example.jimrat.norestcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterPageController {

    @GetMapping("/register")
    public String getRegisterPage(){
        return "registerpage.html";
    }
}

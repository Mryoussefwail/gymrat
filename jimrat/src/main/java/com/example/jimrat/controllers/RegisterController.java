package com.example.jimrat.controllers;

import com.example.jimrat.models.User;
import com.example.jimrat.repositories.RegisterRepository;
import com.example.jimrat.services.LoggedUserManagmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final RegisterRepository registerRepository;
    private final LoggedUserManagmentService loggedUserManagmentService;
    public RegisterController(RegisterRepository registerRepository,LoggedUserManagmentService loggedUserManagmentService){
        this.loggedUserManagmentService=loggedUserManagmentService;
        this.registerRepository=registerRepository;
    }

    @PostMapping
    public String registerUser(@RequestBody User user,@RequestParam String type){
        System.out.println("in register");
        if(type.equals("coach")){

            registerRepository.registerNewCoach(user);

        }
        else {
            registerRepository.registerNewTrainer(user);

        }
        return "redirect:/login";
    }

}

package com.example.jimrat.controllers;

import com.example.jimrat.models.User;
import com.example.jimrat.repositories.RegisterRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final RegisterRepository registerRepository;
    public RegisterController(RegisterRepository registerRepository){
        this.registerRepository=registerRepository;
    }

    @PostMapping
    public void registerUser(@RequestBody User user,@RequestParam String type){
        if(type.equals("coach")){

            registerRepository.registerNewCoach(user);
        }
        else {
            registerRepository.registerNewTrainer(user);
        }
    }

}

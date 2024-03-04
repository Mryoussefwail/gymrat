package com.example.jimrat.controllers;

import com.example.jimrat.models.Coach;
import com.example.jimrat.models.Trainer;
import com.example.jimrat.repositories.CoachRepository;
import com.example.jimrat.services.LoggedUserManagmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coach")
public class CoachController {
    private CoachRepository coachRepository;
    public CoachController(CoachRepository coachRepository){
        this.coachRepository=coachRepository;
    }
    @GetMapping
    public List<Trainer> getAllSubsTrainers(@RequestBody LoggedUserManagmentService loggedUserManagmentService){
        return coachRepository.getAllSupsTrainers(loggedUserManagmentService);
    }
    @PostMapping
    public void registerInGym(@RequestBody LoggedUserManagmentService loggedUserManagmentService,@RequestParam String gymname){
        coachRepository.registerInGym((int) loggedUserManagmentService.getId(),gymname);
    }
}

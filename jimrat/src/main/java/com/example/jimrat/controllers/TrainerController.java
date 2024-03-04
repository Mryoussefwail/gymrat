package com.example.jimrat.controllers;

import com.example.jimrat.models.Coach;
import com.example.jimrat.repositories.TrainerRepository;
import com.example.jimrat.services.LoggedUserManagmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
    private TrainerRepository trainerRepository;
    public TrainerController(TrainerRepository trainerRepository){
        this.trainerRepository=trainerRepository;
    }

    @GetMapping()
    public Coach getSupCoach(@RequestBody LoggedUserManagmentService loggedUserManagmentService){
        return trainerRepository.getCaoch(loggedUserManagmentService);
    }
    @PostMapping("/subscribecoach")
    public void subscribeWithCoach(@RequestBody LoggedUserManagmentService loggedUserManagmentService,@RequestParam int coachId){
        trainerRepository.subscribeWithCoach(loggedUserManagmentService,coachId);
    }
    @PostMapping("/subscribegym")
    public void subscribeInGym(@RequestBody LoggedUserManagmentService loggedUserManagmentService,@RequestParam int gymID){
        trainerRepository.subscribeInGym(loggedUserManagmentService,gymID);
    }
    @GetMapping("/allcoach")
    public List<Coach> getAllCoaches(){
        return trainerRepository.getAllCoaches();
    }
}

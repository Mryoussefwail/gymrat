package com.example.jimrat.controllers;

import com.example.jimrat.models.Gym;
import com.example.jimrat.models.User;
import com.example.jimrat.repositories.CoachRepository;
import com.example.jimrat.repositories.GymRepository;
import com.example.jimrat.services.LoggedUserManagmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gym")
public class RegisterGymController {

    GymRepository gymRepository;
    CoachRepository coachRepository;
    public RegisterGymController(GymRepository gymRepository,CoachRepository coachRepository){
        this.gymRepository=gymRepository;
        this.coachRepository=coachRepository;

    }

    @PostMapping
    public void storeGym(@RequestBody Gym gym){
        this.gymRepository.storeGym(gym);
    }
    @GetMapping
    public List<Gym> getAllGyms(){
        return this.gymRepository.getAllGyms();
    }
    @PostMapping("/jim")
    public void getGymById(@RequestParam String name ,@RequestBody LoggedUserManagmentService user){

         this.coachRepository.registerInGym(Math.toIntExact(user.getId()),name);
    }
}

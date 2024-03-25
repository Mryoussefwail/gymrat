package com.example.jimrat.controllers;

import com.example.jimrat.models.Coach;
import com.example.jimrat.models.Gym;
import com.example.jimrat.models.Trainer;
import com.example.jimrat.repositories.CoachRepository;
import com.example.jimrat.repositories.TrainerRepository;
import com.example.jimrat.services.LoggedUserManagmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/statistics")
public class StatsiticsController {
    private final LoggedUserManagmentService loggedUserManagmentService;
    private CoachRepository coachRepository;
    private TrainerRepository trainerRepository;
    public StatsiticsController(LoggedUserManagmentService loggedUserManagmentService,CoachRepository coachRepository,TrainerRepository trainerRepository){
        this.coachRepository=coachRepository;
        this.loggedUserManagmentService=loggedUserManagmentService;
        this.trainerRepository =trainerRepository;
    }

    @GetMapping
    public String getStatisticsPage(Model model){
        if (loggedUserManagmentService.getEmail()==null){
            return "redirect:/login";
        }
        List<Coach> coaches=trainerRepository.getAllCoaches();
        model.addAttribute("coaches",coaches);
        model.addAttribute("coachesnumber",coaches.size());
        List<Gym> gyms=trainerRepository.getAllGyms();
        model.addAttribute("gyms",gyms);
        model.addAttribute("gymsnumber",gyms.size());
        double average = coaches.size()/gyms.size();
        model.addAttribute("average",average);
        for (int i=0;i<coaches.size();i++){
            coaches.get(i).setTrainers((ArrayList<Trainer>) coachRepository.getAllSupsTrainers(coaches.get(i).getId()));
        }
        model.addAttribute("trcoaches",coaches);
        return "statistics.html";

    }
}

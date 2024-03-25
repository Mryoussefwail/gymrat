package com.example.jimrat.controllers;

import com.example.jimrat.models.Coach;
import com.example.jimrat.models.Image;
import com.example.jimrat.models.Video;
import com.example.jimrat.repositories.ImageRepository;
import com.example.jimrat.repositories.TrainerRepository;
import com.example.jimrat.repositories.VideoRepository;
import com.example.jimrat.services.LoggedUserManagmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class TrainerController {
    private final LoggedUserManagmentService loggedUserManagmentService;
    private ImageRepository imageRepository;
    private TrainerRepository trainerRepository;
    private VideoRepository videoRepository;
    public TrainerController(TrainerRepository trainerRepository , LoggedUserManagmentService loggedUserManagmentService,ImageRepository imageRepository,VideoRepository videoRepository){
        this.imageRepository=imageRepository;
        this.loggedUserManagmentService=loggedUserManagmentService;
        this.trainerRepository=trainerRepository;
        this.videoRepository=videoRepository;
    }

    @GetMapping("/trainer")
    public Coach getSupCoach(){
        return trainerRepository.getCaoch((int) loggedUserManagmentService.getId());
    }
    @PostMapping("/subscribecoach")
    public String subscribeWithCoach(@RequestParam int coachId){
        System.out.println("in subscribe");
        trainerRepository.subscribeWithCoach(loggedUserManagmentService,coachId);
        return "redirect:/profile";
    }
    @PostMapping("/subscribegym")
    public String subscribeInGym(@RequestParam int gymID){
        trainerRepository.subscribeInGym((int) loggedUserManagmentService.getId(),gymID);
        return "redirect:/profile";
    }
    @GetMapping("/allcoach")
    public List<Coach> getAllCoaches(){
        return trainerRepository.getAllCoaches();
    }
    @PostMapping("/profile")
    public void changeImage(@RequestBody MultipartFile file) throws IOException {
        System.out.println("in change image");
        Image image=new Image();
        image.setId(0L);
        image.setName(file.getName());
        image.setFilePath(file.getOriginalFilename());
        image.setImageData(file.getBytes());
        image.setType(file.getContentType());
        imageRepository.storeImage(image, (int) loggedUserManagmentService.getId(),loggedUserManagmentService.getType());
    }

}

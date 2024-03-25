package com.example.jimrat.controllers;

import com.example.jimrat.models.*;
import com.example.jimrat.repositories.CoachRepository;
import com.example.jimrat.repositories.GymRepository;
import com.example.jimrat.repositories.ImageRepository;
import com.example.jimrat.repositories.TrainerRepository;
import com.example.jimrat.services.LoggedUserManagmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller

public class RegisterGymController {
    private final LoggedUserManagmentService loggedUserManagmentService;
    GymRepository gymRepository;
    CoachRepository coachRepository;
    TrainerRepository trainerRepository;
    private ImageRepository imageRepository;
    public RegisterGymController(GymRepository gymRepository,CoachRepository coachRepository,LoggedUserManagmentService loggedUserManagmentService,ImageRepository imageRepository,TrainerRepository trainerRepository){
        this.gymRepository=gymRepository;
        this.coachRepository=coachRepository;
        this.loggedUserManagmentService=loggedUserManagmentService;
        this.imageRepository=imageRepository;
        this.trainerRepository=trainerRepository;
    }

    @PostMapping("/gymregister")
    public void storeGym(@RequestBody Gym gym){
        this.gymRepository.storeGym(gym);
    }
    @GetMapping("/gymregister")
    public String getRegisterGymPage(){

        return "gymregisterpage";
    }
    @GetMapping("/gymprofile")
    public String gymProfile(Model model){
        if(loggedUserManagmentService.getEmail()==null){
            return "redirect:/login";
        }
        model.addAttribute("User",loggedUserManagmentService);
        Image image=gymRepository.getProfileImage();
        if (image!=null){
            String base64Image= Base64.getEncoder().encodeToString(image.getImageData());
            model.addAttribute("dataurl",base64Image);
        }
        return "gymprofile";
    }
    @PostMapping("/gymprofile")
    public void changeGymImageProfile(@RequestBody MultipartFile file) throws IOException {
        System.out.println("in change image");
        Image image=new Image();
        image.setId(0L);
        image.setName(file.getName());
        image.setFilePath(file.getOriginalFilename());
        image.setImageData(file.getBytes());
        image.setType(file.getContentType());
        imageRepository.storeImage(image, (int) loggedUserManagmentService.getId(),loggedUserManagmentService.getType());
    }
    @GetMapping("/gymsubstrainees")
    public String getSubsTraineesPage(Model model){
        List<Trainer> trainers= trainerRepository.getAllTrainersinGymId((int) loggedUserManagmentService.getId());
        model.addAttribute("trainers",trainers);
        model.addAttribute("name",loggedUserManagmentService.getName());
        List<Image> images=new ArrayList<>();
        List<String> base64Image=new ArrayList<>();
        List<ViewTrainer> dataUrl=new ArrayList<>();
        for (int i=0;i<trainers.size();i++){
            if(trainers.get(i).getImageId()==0 ){
                images.add(new Image());
                base64Image.add(null);
                dataUrl.add(new ViewTrainer(trainers.get(i),null));
                continue;
            }
            images.add(imageRepository.getImage(trainers.get(i).getImageId()));
            base64Image.add(Base64.getEncoder().encodeToString(images.get(i).getImageData()));
            dataUrl.add(new ViewTrainer(trainers.get(i),Base64.getEncoder().encodeToString(images.get(i).getImageData())));

        }
        model.addAttribute("dataurl",dataUrl);
        return "gymsubscribedtraineepage.html";
    }

}

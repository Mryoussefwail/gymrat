package com.example.jimrat.norestcontrollers;

import com.example.jimrat.models.*;
import com.example.jimrat.repositories.*;
import com.example.jimrat.services.LoggedUserManagmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller

public class TrainerPageController {
    private final LoggedUserManagmentService loggedUserManagmentService;
    private ImageRepository imageRepository;
    private TrainerRepository trainerRepository;
    private CoachRepository coachRepository;
    private GymRepository gymRepository;
    private VideoRepository videoRepository;
    public TrainerPageController (TrainerRepository trainerRepository,GymRepository gymRepository,CoachRepository coachRepository,ImageRepository imageRepository,LoggedUserManagmentService loggedUserManagmentService,VideoRepository videoRepository){
        this.imageRepository=imageRepository;
        this.coachRepository=coachRepository;
        this.trainerRepository=trainerRepository;
        this.gymRepository=gymRepository;
        this.loggedUserManagmentService=loggedUserManagmentService;
        this.videoRepository=videoRepository;
    }
    @GetMapping("/hometrainer")
    public String getTrainerPage(Model model){
        if(loggedUserManagmentService.getEmail()==null){
            return "redirect:/login";
        }
        List<Coach> coaches=trainerRepository.getAllCoaches();
        model.addAttribute("coaches",coaches);
        List<Image> images=new ArrayList<>();
        List<String> base64Image=new ArrayList<>();
        List<viewCoach> dataUrl=new ArrayList<>();
        for (int i=0;i<coaches.size();i++){
            if(coaches.get(i).getImageId()==0 ){
                images.add(new Image());
                base64Image.add(null);
                dataUrl.add(new viewCoach(coaches.get(i),null));
                continue;
            }
            images.add(imageRepository.getImage(coaches.get(i).getImageId()));
            base64Image.add(Base64.getEncoder().encodeToString(images.get(i).getImageData()));
            dataUrl.add(new viewCoach(coaches.get(i),Base64.getEncoder().encodeToString(images.get(i).getImageData())));

        }

        model.addAttribute("dataurl",dataUrl);
        return "hometrainer.html";
    }
    @GetMapping("/gymdetails")
    public String getGymdetailsPage(@RequestParam int id, Model model){
        if(loggedUserManagmentService.getEmail()==null){
            return "redirect:/login";
        }
        Gym gym=gymRepository.getGymById(id);
        model.addAttribute("gym",gym);
        List<Coach> coaches=coachRepository.getAllCoachesinGymID(gym.getId());
        List<Trainer> trainers=trainerRepository.getAllTrainersinGymId(gym.getId());
        model.addAttribute("coaches",coaches);
        model.addAttribute("trainers",trainers);
        return "gymDetailsPage.html";

    }
    @GetMapping("/trainergyms")
    public String getGymsPageForTrainers(Model model){
        if(loggedUserManagmentService.getEmail()==null){
            return "redirect:/login";
        }
        List<Gym> gyms=gymRepository.getAllGyms();
        model.addAttribute("gyms",gyms);
        List<Image> images=new ArrayList<>();
        List<String> base64Image=new ArrayList<>();
        List<ViewGym> dataUrl=new ArrayList<>();
        for (int i=0;i<gyms.size();i++){
            if(gyms.get(i).getImageId()==0 ){
                images.add(new Image());
                base64Image.add(null);
                dataUrl.add(new ViewGym(gyms.get(i),null));
                continue;
            }
            images.add(imageRepository.getImage(gyms.get(i).getImageId()));
            base64Image.add(Base64.getEncoder().encodeToString(images.get(i).getImageData()));
            dataUrl.add(new ViewGym(gyms.get(i),Base64.getEncoder().encodeToString(images.get(i).getImageData())));

        }

        model.addAttribute("dataurl",dataUrl);
        return "homeTrainerGyms.html";
    }
    @GetMapping("/profile")
    public String getProfilePage(Model model){
        if(loggedUserManagmentService.getEmail()==null){
            return "redirect:/login";
        }
        System.out.println(loggedUserManagmentService.getName());
        model.addAttribute("User",loggedUserManagmentService);
        Image image=trainerRepository.getProfileImage();
        if (image!=null){

            String base64Image= Base64.getEncoder().encodeToString(image.getImageData());
            model.addAttribute("dataurl",base64Image);
        }
        Coach coach=trainerRepository.getSubscripedCoach((int) loggedUserManagmentService.getId());
        if (coach!=null){
            model.addAttribute("coach",coach);
        }
        return "profile.html";
    }
    @GetMapping("/trainerreels")
    public String getReelsPage(Model model){
        if(loggedUserManagmentService.getEmail()==null){
            return "redirect:/login";
        }
        List<Video>videos=videoRepository.getAllVideos();
        List<String> base64Image=new ArrayList<>();
        List<ViewVideo>views=new ArrayList<>();
        if (videos!=null){

            for (int i=0;i<videos.size();i++)
            {
                String ba=Base64.getEncoder().encodeToString(videos.get(i).getImageData());
                base64Image.add("data:video/mp4;base64,"+ba);
                views.add(new ViewVideo(videos.get(i),base64Image.get(i)));
            }
            model.addAttribute("videos",views);
        }

        return "trainerReels.html";
    }


    @GetMapping("/coachdetailspage")
    public String getCoachDetails(@RequestParam int id,Model model){
        if (loggedUserManagmentService.getEmail()==null){
            return "redirect:/login";
        }
        Coach coach=coachRepository.getCoachByID(id);
        model.addAttribute("coach",coach);
        List<Trainer>trainers=coachRepository.getAllSupsTrainers(id);
        if(trainers.size()!=0){

            model.addAttribute("trainees",trainers);
        }
        Gym gym=gymRepository.getGymById(coach.getGymID());
        if(gym!=null){

            model.addAttribute("gym",gym);
        }
        return "coachDetailsPage.html";
    }
    @PostMapping("/addreel")
    public void addReelToVideo(@RequestBody MultipartFile file) throws IOException {
        System.out.println("in add video");
        Video video=new Video();
        video.setId(0L);
        video.setName(file.getName());
        video.setFilePath(file.getOriginalFilename());
        video.setImageData(file.getBytes());
        video.setType(file.getContentType());
        video.setUsertype(loggedUserManagmentService.getType());
        video.setUserid(loggedUserManagmentService.getId());
        videoRepository.addVideo(video);
    }
}

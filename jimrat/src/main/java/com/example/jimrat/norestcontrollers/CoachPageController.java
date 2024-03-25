package com.example.jimrat.norestcontrollers;

import com.example.jimrat.models.*;
import com.example.jimrat.repositories.CoachRepository;
import com.example.jimrat.repositories.ImageRepository;
import com.example.jimrat.repositories.VideoRepository;
import com.example.jimrat.services.LoggedUserManagmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class CoachPageController {
    private CoachRepository coachRepository;
    private VideoRepository videoRepository;
    private ImageRepository imageRepository;
    private final LoggedUserManagmentService loggedUserManagmentService;
    public CoachPageController(CoachRepository coachRepository,LoggedUserManagmentService loggedUserManagmentService,ImageRepository imageRepository,VideoRepository videoRepository){
        this.coachRepository=coachRepository;
        this.videoRepository=videoRepository;
        this.loggedUserManagmentService=loggedUserManagmentService;
        this.imageRepository=imageRepository;
    }
@GetMapping("/homecoach")
    public String getCoachHome(Model model){
        if(loggedUserManagmentService.getEmail()==null){
            return "redirect:/login";
        }
        System.out.println(loggedUserManagmentService.getName());
        List<Trainer> trainers =coachRepository.getAllSupsTrainers((int) loggedUserManagmentService.getId());
       // System.out.println(trainers.get(0).getName());
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
        return "homecoach.html";
    }
    @GetMapping("/profilecoach")
    public String getCoachProfile(Model model){
        if (loggedUserManagmentService.getEmail()==null){
            return "redirect:/login";
        }
        model.addAttribute("User",loggedUserManagmentService);
        Image image=coachRepository.getProfileImage();
        String base64Image= Base64.getEncoder().encodeToString(image.getImageData());
        model.addAttribute("dataurl",base64Image);
        return "profilecoach.html";
    }
    @PostMapping("/profilecoach")
    public void getCoachProfile(@RequestBody MultipartFile file) throws IOException {
        System.out.println("in change image");
        Image image=new Image();
        image.setId(0L);
        image.setName(file.getName());
        image.setFilePath(file.getOriginalFilename());
        image.setImageData(file.getBytes());
        image.setType(file.getContentType());
        imageRepository.storeImage(image, (int) loggedUserManagmentService.getId(),loggedUserManagmentService.getType());
    }
    @GetMapping("/coachreels")
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

        return "coachreels.html";
    }

}

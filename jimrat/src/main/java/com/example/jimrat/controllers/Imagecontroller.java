package com.example.jimrat.controllers;

import com.example.jimrat.models.Image;
import com.example.jimrat.repositories.ImageRepository;
import com.example.jimrat.services.LoggedUserManagmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/photo")
public class Imagecontroller {
    ImageRepository imageRepository;
    private final LoggedUserManagmentService loggedUserManagmentService;
    public Imagecontroller(ImageRepository imageRepository,LoggedUserManagmentService loggedUserManagmentService){
        this.imageRepository=imageRepository;
        this.loggedUserManagmentService=loggedUserManagmentService;
    }
    @PostMapping
    public void storePhoto(@RequestParam int id,@RequestParam String type,@RequestBody MultipartFile file) throws IOException {
        Image image=new Image();
        image.setId(0L);
        image.setName(file.getName());
        image.setFilePath(file.getOriginalFilename());
        image.setImageData(file.getBytes());
        image.setType(file.getContentType());
        imageRepository.storeImage(image,id,type);
    }
    @GetMapping
    public Image getPhoto(@RequestParam int image_id)
    {

        return imageRepository.getImage(image_id);
    }
}

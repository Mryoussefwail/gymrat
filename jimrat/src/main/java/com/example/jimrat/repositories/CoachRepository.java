package com.example.jimrat.repositories;

import com.example.jimrat.models.Coach;
import com.example.jimrat.models.Image;
import com.example.jimrat.models.Trainer;
import com.example.jimrat.rowmappers.CoachRowMapper;
import com.example.jimrat.rowmappers.ImageRowMapper;
import com.example.jimrat.rowmappers.TrainerRowMapper;
import com.example.jimrat.services.LoggedUserManagmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.io.Console;
import java.net.http.HttpResponse;
import java.util.List;

@Repository
public class CoachRepository {
    private JdbcTemplate jdbc;
    private GymRepository gymRepository;
    private ImageRepository imageRepository;
    private final LoggedUserManagmentService loggedUserManagmentService;
    public CoachRepository(JdbcTemplate jdbcTemplate, GymRepository gymRepository ,ImageRepository imageRepository, LoggedUserManagmentService loggedUserManagmentService){
        this.imageRepository=imageRepository;
        this.jdbc=jdbcTemplate;
        this.gymRepository=gymRepository;
        this.loggedUserManagmentService=loggedUserManagmentService;
    }
    public void registerInGym(int id,String gymName){
        System.out.println("in register");
        int gymid= gymRepository.getGymId(gymName);
        if(gymid==0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        String sql="UPDATE gymrat.coach SET GymID = ? where CoachID = ?";
        jdbc.update(sql,gymid,id);
    }
    public List<Trainer> getAllSupsTrainers(int id){
        String sql="select * from gymrat.trainer where gymrat.trainer.CoachID = ?";
        return jdbc.query(sql,new TrainerRowMapper(),id);
    }
    public void changeProfileImage(Image image){
        imageRepository.storeImage(image, (int) loggedUserManagmentService.getId(),loggedUserManagmentService.getType());
    }
    public Image getProfileImage() {
        String sql="select * from gymrat.coach where CoachID = ?";
        List<Coach> coaches=jdbc.query(sql,new CoachRowMapper(),loggedUserManagmentService.getId());
        return imageRepository.getImage(coaches.get(0).getImageId());
    }
    public List<Coach> getAllCoachesinGymID(int id){
        String sql="select * from gymrat.coach where GymID = ?";
        List<Coach> coaches=jdbc.query(sql,new CoachRowMapper(),id);
        return coaches;
    }

    public Coach getCoachByID(int id) {
        String sql="select * from gymrat.coach where CoachID = ?";
        List<Coach> coaches=jdbc.query(sql,new CoachRowMapper(),id);
        if (coaches.size()==0){
            return null;
        }
        else return coaches.get(0);
    }
}

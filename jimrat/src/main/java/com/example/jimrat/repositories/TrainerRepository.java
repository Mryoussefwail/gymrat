package com.example.jimrat.repositories;

import com.example.jimrat.controllers.LoginController;
import com.example.jimrat.models.*;
import com.example.jimrat.rowmappers.CoachRowMapper;
import com.example.jimrat.rowmappers.GymRowMapper;
import com.example.jimrat.rowmappers.TrainerRowMapper;
import com.example.jimrat.services.LoggedUserManagmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public class TrainerRepository {
    private JdbcTemplate jdbc;
    private ImageRepository imageRepository;
    private final LoggedUserManagmentService loggedUserManagmentService;
    public TrainerRepository(JdbcTemplate jdbcTemplate, LoggedUserManagmentService loggedUserManagmentService,ImageRepository imageRepository){
        this.jdbc=jdbcTemplate;
        this.loggedUserManagmentService=loggedUserManagmentService;
        this.imageRepository=imageRepository;
    }
    public void subscribeWithCoach(LoggedUserManagmentService loggedUserManagmentService,int coachID){
        String sql="update gymrat.trainer set CoachID = ? where UserID = ?";
        jdbc.update(sql,coachID,Math.toIntExact(loggedUserManagmentService.getId()));
    }
    public void subscribeInGym(int trainerId,int gymId){
        String sql="update gymrat.trainer set GymID = ? where UserID = ?";
        jdbc.update(sql,gymId,trainerId);
    }
    public Coach getCaoch(int id){
        String sql="select * from gymrat.coach  where  gymrat.coach.CoachID in (select CoachID from gymrat.trainer where UserID = ? ); ";
        List<Coach> coach =jdbc.query(sql,new  CoachRowMapper(),id);
        if(coach.size()==0){
            return null;
        }
        else {return coach.get(0);}
    }
    public Gym getGym(){
        String sql="select * from gymrat.gym  where  gymrat.gym.GymID in (select GymID from gymrat.trainer where UserID = ? ); ";
        List<Gym> gyms =jdbc.query(sql,new GymRowMapper(),loggedUserManagmentService.getId());
        if(gyms.size()==0){
            return null;
        }
        else {return gyms.get(0);}
    }
    public List<Coach> getAllCoaches(){
        String sql="select * from gymrat.coach";
        return jdbc.query(sql,new CoachRowMapper());
    }
    public List<Gym> getAllGyms(){
        String sql="select * from gymrat.gym";
        return jdbc.query(sql,new GymRowMapper());
    }
    public void changeProfileImage(Image image){
        imageRepository.storeImage(image, (int) loggedUserManagmentService.getId(),loggedUserManagmentService.getType());
    }
    public Image getProfileImage() {
        String sql="select * from gymrat.trainer where UserID = ?";
        System.out.println(loggedUserManagmentService.getName());
        List<Trainer> trainers=jdbc.query(sql,new TrainerRowMapper(),loggedUserManagmentService.getId());
        if (trainers.size()==0){
            return null;
        }
        return imageRepository.getImage(trainers.get(0).getImageId());
    }
    public List<Trainer> getAllTrainersinGymId(int id){
        String sql="select * from gymrat.trainer where GymID = ?";
        List<Trainer> trainers=jdbc.query(sql,new TrainerRowMapper(),id);
        return trainers;
    }

    public Coach getSubscripedCoach(int id) {
        String sql="select * from gymrat.coach join gymrat.trainer on gymrat.coach.CoachID = gymrat.trainer.CoachID where UserID = ?";
        List<Coach>coaches=jdbc.query(sql,new CoachRowMapper(),id);
        if (coaches.size()==0){
            return null;
        }
        return coaches.get(0);
    }
}

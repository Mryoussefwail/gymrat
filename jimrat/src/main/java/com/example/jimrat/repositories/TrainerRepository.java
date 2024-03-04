package com.example.jimrat.repositories;

import com.example.jimrat.controllers.LoginController;
import com.example.jimrat.models.Coach;
import com.example.jimrat.models.Gym;
import com.example.jimrat.models.User;
import com.example.jimrat.rowmappers.CoachRowMapper;
import com.example.jimrat.rowmappers.GymRowMapper;
import com.example.jimrat.services.LoggedUserManagmentService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public class TrainerRepository {
    private JdbcTemplate jdbc;
    private LoggedUserManagmentService loggedUserManagmentService;
    public TrainerRepository(JdbcTemplate jdbcTemplate, LoggedUserManagmentService loggedUserManagmentService){
        this.jdbc=jdbcTemplate;
        this.loggedUserManagmentService=loggedUserManagmentService;
    }
    public void subscribeWithCoach(LoggedUserManagmentService loggedUserManagmentService,int coachID){
        String sql="update gymrat.trainer set CoachID = ? where UserID = ?";
        jdbc.update(sql,coachID,Math.toIntExact(loggedUserManagmentService.getId()));
    }
    public void subscribeInGym(LoggedUserManagmentService loggedUserManagmentService,int gymId){
        String sql="update gymrat.trainer set GymID = ? where UserID = ?";
        jdbc.update(sql,gymId,Math.toIntExact(loggedUserManagmentService.getId()));
    }
    public Coach getCaoch(LoggedUserManagmentService loggedUserManagmentService){
        String sql="select * from gymrat.coach  where  gymrat.coach.CoachID in (select CoachID from gymrat.trainer where UserID = ? ); ";
        List<Coach> coach =jdbc.query(sql,new  CoachRowMapper(),loggedUserManagmentService.getId());
        System.out.println(loggedUserManagmentService.getId());
        if(coach.size()==0){
            return null;
        }
        else {return coach.get(0);}
    }
    public Gym getGym(LoggedUserManagmentService loggedUserManagmentService){
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
}

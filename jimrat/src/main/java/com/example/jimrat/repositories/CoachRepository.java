package com.example.jimrat.repositories;

import com.example.jimrat.models.Trainer;
import com.example.jimrat.rowmappers.TrainerRowMapper;
import com.example.jimrat.services.LoggedUserManagmentService;
import org.springframework.http.HttpStatus;
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
    public CoachRepository(JdbcTemplate jdbcTemplate, GymRepository gymRepository){
        this.jdbc=jdbcTemplate;
        this.gymRepository=gymRepository;
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
    public List<Trainer> getAllSupsTrainers(LoggedUserManagmentService loggedUserManagmentService){
        String sql="select * from gymrat.trainer where gymrat.trainer.CoachID = ?";
        return jdbc.query(sql,new TrainerRowMapper(),loggedUserManagmentService.getId());
    }
}

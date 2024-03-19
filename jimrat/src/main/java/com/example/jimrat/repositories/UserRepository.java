package com.example.jimrat.repositories;

import com.example.jimrat.models.Coach;
import com.example.jimrat.models.Trainer;
import com.example.jimrat.models.User;
import com.example.jimrat.rowmappers.CoachRowMapper;
import com.example.jimrat.rowmappers.TrainerRowMapper;
import com.example.jimrat.rowmappers.UsersRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.security.PublicKey;
import java.util.List;

@Repository
public class UserRepository {
    private JdbcTemplate jdbc;
    public UserRepository(JdbcTemplate jdbcTemplate){
        this.jdbc=jdbcTemplate;
    }

    public List<User> getAllCoaches(){
        String sql="select * from gymrat.coach";
        return jdbc.query(sql,new CoachRowMapper());
    }
    public List<User> getAllTrainers(){
        String sql="select * from gymrat.trainer ";
        return jdbc.query(sql,new TrainerRowMapper());
    }
    public User getUserByEmail(String email){
        String sql="select * from gymrat.trainer where Email = ?";
        List<Trainer> trainers=  jdbc.query(sql,new TrainerRowMapper(),email);
        if(trainers.size() ==0){
            String sql2="select * from gymrat.coach where Email = ?";
            List<Coach>coaches=jdbc.query(sql2,new CoachRowMapper(),email);
            if(coaches.size()==0){
                return null;
            }
            else return coaches.get(0);
        }
        return trainers.get(0);
    }
}

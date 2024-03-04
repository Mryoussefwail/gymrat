package com.example.jimrat.repositories;

import com.example.jimrat.models.Coach;
import com.example.jimrat.models.Trainer;
import com.example.jimrat.models.User;
import com.example.jimrat.rowmappers.UsersRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegisterRepository {
    private JdbcTemplate jdbc;
    public RegisterRepository(JdbcTemplate jdbcTemplate){
        this.jdbc=jdbcTemplate;
    }
    public void registerNewCoach(User coach){
        String sql="insert into gymrat.coach values (NULL, ?, ?, ?, ?, ?, ?, NULL)";
        jdbc.update(sql,coach.getName(),coach.getEmail(),coach.getPassword(),coach.getPhone(),coach.getGender(),"coach");
    }
    public void registerNewTrainer(User trainer){
        String sql="insert into gymrat.trainer values (NULL, ?, ?, ?, ?, ?, ?, NULL, NULL)";
        jdbc.update(sql,trainer.getName(),trainer.getEmail(),trainer.getPassword(),trainer.getPhone(),trainer.getGender(),"trainer");
    }


}

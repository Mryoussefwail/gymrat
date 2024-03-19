package com.example.jimrat.rowmappers;

import com.example.jimrat.models.Trainer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainerRowMapper implements RowMapper {
    @Override
    public Trainer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Trainer trainer= new Trainer();
        trainer.setId(rs.getLong("UserID"));
        trainer.setName(rs.getString("Name"));
        trainer.setEmail(rs.getString("Email"));
        trainer.setPassword(rs.getString("Password"));
        trainer.setPhone(rs.getString("Phone"));
        trainer.setGender(rs.getString("Gender"));
        trainer.setType(rs.getString("Type"));
        trainer.setCoach(rs.getInt("CoachID"));
        trainer.setGym(rs.getInt("GymID"));
        trainer.setImageId(rs.getInt("ImageID"));
        return trainer;
    }
}

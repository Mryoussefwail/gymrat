package com.example.jimrat.rowmappers;

import com.example.jimrat.models.Coach;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoachRowMapper implements RowMapper {
    @Override
    public Coach mapRow(ResultSet rs, int rowNum) throws SQLException {
        Coach coach=new Coach();
        coach.setId(rs.getLong("CoachID"));
        coach.setName(rs.getString("Name"));
        coach.setEmail(rs.getString("Email"));
        coach.setPassword(rs.getString("Password"));
        coach.setPhone(rs.getString("Phone"));
        coach.setGender(rs.getString("Gender"));
        coach.setType(rs.getString("Type"));
        coach.setGymID(rs.getInt("GymID"));
        return coach;
    }
}

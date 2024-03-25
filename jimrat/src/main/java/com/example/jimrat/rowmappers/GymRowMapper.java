package com.example.jimrat.rowmappers;

import com.example.jimrat.models.Gym;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class GymRowMapper implements RowMapper {


    @Override
    public Gym mapRow(ResultSet rs, int rowNum) throws SQLException {
        Gym gym=new Gym();
        gym.setId(rs.getInt("GymID"));
        gym.setName(rs.getString("GymName"));
        gym.setLatitude(rs.getDouble("Latitude"));
        gym.setLongitude(rs.getDouble("Longitude"));
        gym.setImageId(rs.getInt("ImageID"));
        gym.setPassword(rs.getString("password"));
        gym.setMonthPrice(rs.getDouble("monthPrice"));
        gym.setHalfyearPrice(rs.getDouble("halfyearPrice"));
        gym.setYearPrice(rs.getDouble("yearPrice"));
        return gym;
    }
}

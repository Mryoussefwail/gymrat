package com.example.jimrat.repositories;

import com.example.jimrat.models.Image;

import com.example.jimrat.rowmappers.ImageRowMapper;
import com.example.jimrat.services.LoggedUserManagmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageRepository {
    private JdbcTemplate jdbc;

    public ImageRepository(JdbcTemplate jdbcTemplate ){
        this.jdbc=jdbcTemplate;

    }

    public void storeImage(Image file,int id,String type){
        String sql="insert into gymrat.image values (null , ?,?,?,?);";
        jdbc.update(sql,file.getName(),file.getType(),file.getFilePath(),file.getImageData());
        String sq2="select * from gymrat.image where filePath = ?";
        List<Image> files=jdbc.query(sq2,new ImageRowMapper(),file.getFilePath());
        if(type.equals("trainer")){
            String sql3="update gymrat.trainer set ImageID = ? where UserID = ?";
            jdbc.update(sql3,files.get(0).getId(),id);
        }
        else if(type.equals("coach")) {
            String sql3="update gymrat.coach set ImageID = ? where CoachID = ?";
            jdbc.update(sql3,files.get(0).getId(),id);
        }
        else if(type.equals("gym")){
            String sql3="update gymrat.gym set ImageID = ? where GymID = ?";
            jdbc.update(sql3,files.get(0).getId(),id);
        }


    }
    public Image getImage(int id){
        String sql="select * from gymrat.image where ID = ?";
        List<Image> files=jdbc.query(sql, new ImageRowMapper(),id);
        if (files.size()==0){
            return null;
        }
        else {

            return files.get(0);
        }
    }
}

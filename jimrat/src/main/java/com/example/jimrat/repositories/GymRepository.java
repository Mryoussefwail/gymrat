package com.example.jimrat.repositories;

import com.example.jimrat.models.Gym;
import com.example.jimrat.models.Image;
import com.example.jimrat.rowmappers.GymRowMapper;
import com.example.jimrat.services.LoggedUserManagmentService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GymRepository {
    private JdbcTemplate jdbc;
    private ImageRepository imageRepository;
    private LoggedUserManagmentService loggedUserManagmentService;
    public GymRepository(JdbcTemplate jdbcTemplate,ImageRepository imageRepository,LoggedUserManagmentService loggedUserManagmentService)
    {
        this.jdbc=jdbcTemplate;
        this.imageRepository=imageRepository;
        this.loggedUserManagmentService=loggedUserManagmentService;
    }
    public List<Gym> getAllGyms(){
        String sql="select * from gymrat.gym";
        return jdbc.query(sql,new GymRowMapper());
    }
    public Gym getGymById(int id){
        String sql="select * from gymrat.gym where GymID = ?";
        List<Gym>gym= jdbc.query(sql,new GymRowMapper(),id);
        if (gym.size()==0){
            return null;
        }
        else {
            return gym.get(0);
        }
    }
    public Gym getGymByName(String name){
        String sql="select * from gymrat.gym where GymName = ?";
        List<Gym>gym= jdbc.query(sql,new GymRowMapper(),name);
        if (gym.size()==0){
            return null;
        }
        else {
            return gym.get(0);
        }
    }
    public void storeGym(Gym gym){
        String sql="insert into gymrat.gym values (null , ?, ?, ?, null)";
        jdbc.update(sql,gym.getName(),gym.getLatitude(),gym.getLongitude());
    }
    public int getGymId(String name){
        String sql="select * from gymrat.gym where GymName = ?";
        List<Gym>gym= jdbc.query(sql,new GymRowMapper(),name);
        if (gym.size()==0){
            return 0;
        }
        else {
            return gym.get(0).getId();
        }
    }
    public void changeProfileImage(Image image){
        imageRepository.storeImage(image, (int) loggedUserManagmentService.getId(),"gym");
    }
    public Image getProfileImage() {
        String sql="select * from gymrat.gym where GymID = ?";
        List<Gym> gyms=jdbc.query(sql,new GymRowMapper(),loggedUserManagmentService.getId());
        return imageRepository.getImage(gyms.get(0).getImageId());
    }

}

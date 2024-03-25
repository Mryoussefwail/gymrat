package com.example.jimrat.repositories;

import com.example.jimrat.models.Gym;
import com.example.jimrat.models.User;
import com.example.jimrat.rowmappers.GymRowMapper;
import com.example.jimrat.rowmappers.UsersRowMapper;
import com.example.jimrat.services.LoggedUserManagmentService;
import com.example.jimrat.services.LoginCountService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginRepository {
    LoginCountService loginCountService;
    UserRepository userRepository;
    JdbcTemplate jdbc;

    public LoginRepository(JdbcTemplate jdbc,LoginCountService loginCountService,UserRepository userRepository){
        this.jdbc=jdbc;
        this.loginCountService=loginCountService;
        this.userRepository=userRepository;
    }


    public User login(String email,String password){

        User user=userRepository.getUserByEmail(email);
        if(user ==null){
            return null;
        }
        if(user.getPassword().equals(password)){

            return user;
        }
        else {
            return null;
        }
    }

    public Gym loginGym(String email, String password) {
        String sql="select * from gymrat.gym where GymName = ?";
        List<Gym>gyms=jdbc.query(sql,new GymRowMapper(),email);
        if (gyms==null){
            return null;
        }
        else return gyms.get(0);
    }
}

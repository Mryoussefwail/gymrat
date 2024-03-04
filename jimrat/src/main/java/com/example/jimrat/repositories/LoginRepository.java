package com.example.jimrat.repositories;

import com.example.jimrat.models.User;
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
    LoggedUserManagmentService loggedUserManagmentService;
    JdbcTemplate jdbc;

    public LoginRepository(JdbcTemplate jdbc,LoggedUserManagmentService loggedUserManagmentService,LoginCountService loginCountService,UserRepository userRepository){
        this.jdbc=jdbc;
        this.loginCountService=loginCountService;
        this.loggedUserManagmentService=loggedUserManagmentService;
        this.userRepository=userRepository;
    }


    public User login(String email,String password){

        User user=userRepository.getUserByEmail(email);
        if(user ==null){
            return null;
        }
        if(user.getPassword().equals(password)){
            loggedUserManagmentService.setEmail(email);
            loggedUserManagmentService.setId(user.getId());
            loggedUserManagmentService.setType(user.getType());
            loginCountService.increment();
            return user;
        }
        else {
            return null;
        }
    }
}

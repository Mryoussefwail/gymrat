package com.example.jimrat.controllers;

import com.example.jimrat.models.User;
import com.example.jimrat.repositories.LoginRepository;
import com.example.jimrat.repositories.UserRepository;
import com.example.jimrat.services.LoggedUserManagmentService;
import org.apache.coyote.BadRequestException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/login")
public class LoginController {
    public LoggedUserManagmentService loggedUserManagmentService;
    private LoginRepository loginRepository;
    private UserRepository userRepository;
    public LoginController(LoginRepository loginRepository,UserRepository userRepository,LoggedUserManagmentService loggedUserManagmentService){
        this.loginRepository=loginRepository;
        this.userRepository=userRepository;
        this.loggedUserManagmentService=loggedUserManagmentService;
    }

    @PostMapping
    public User login(@RequestBody User user){
        User bolean=loginRepository.login(user.getEmail(), user.getPassword());
        if(bolean!=null){
            return bolean;
        }
        else {
            try {
                throw new BadRequestException();
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @GetMapping("/main")
    public String homePage(@RequestParam(required = false) String logout, Model model){
        if (logout!=null){
            loggedUserManagmentService.setEmail(null);
        }
        String username= loggedUserManagmentService.getEmail();
        if(username==null){
            return "redirect:/";
        }
        model.addAttribute("email",username);
        model.addAttribute("id",loggedUserManagmentService.getId());
        model.addAttribute("type",loggedUserManagmentService.getType());
        return "welcomepage.html";
    }


}

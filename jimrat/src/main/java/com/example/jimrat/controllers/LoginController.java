package com.example.jimrat.controllers;

import com.example.jimrat.models.User;
import com.example.jimrat.repositories.LoginRepository;
import com.example.jimrat.repositories.UserRepository;
import com.example.jimrat.services.LoggedUserManagmentService;
import com.example.jimrat.services.LoginCountService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    private final LoggedUserManagmentService loggedUserManagmentService;
    private final LoginCountService loginCountService;

    private LoginRepository loginRepository;
    private UserRepository userRepository;
    public LoginController(LoginRepository loginRepository,UserRepository userRepository,LoggedUserManagmentService loggedUserManagmentService,LoginCountService loginCountService){
        this.loginRepository=loginRepository;
        this.userRepository=userRepository;
        this.loggedUserManagmentService=loggedUserManagmentService;
        this.loginCountService=loginCountService;
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
    @PostMapping("/login")
    public String login(@RequestParam String email,@RequestParam String password){
        System.out.println("in");
        User bolean=loginRepository.login(email, password);
        if(bolean!=null){
            if (bolean.getType().equals("coach")){
                loggedUserManagmentService.setName(bolean.getName());
                loggedUserManagmentService.setEmail(email);
                loggedUserManagmentService.setId(bolean.getId());
                loggedUserManagmentService.setType(bolean.getType());
                loginCountService.increment();
                System.out.println(loggedUserManagmentService.getName());
                return "redirect:/homecoach";
            }
            else {

                loggedUserManagmentService.setName(bolean.getName());
                loggedUserManagmentService.setEmail(email);
                loggedUserManagmentService.setId(bolean.getId());
                loggedUserManagmentService.setType(bolean.getType());
                loginCountService.increment();
                System.out.println(loggedUserManagmentService.getName());
                return "redirect:/hometrainer";
            }
        }
        else {
            return "redirect:/login";
        }
    }
    @GetMapping("/logout")
    public String logout(){
        loggedUserManagmentService.setEmail(null);
        return "redirect:/login";
    }


}

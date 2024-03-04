package com.example.jimrat.models;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class Trainer extends User{
    @Autowired
    int coachID;
    int gymID;

    public int getCoachID() {
        return coachID;
    }

    public void setCoach(int coachid) {
        this.coachID = coachid;
    }

    public int getGymID() {
        return gymID;
    }

    public void setGym(int gym) {
        this.gymID = gym;
    }

    public Trainer(String name, String phone, String email, String password, String gender) {
        super(name, phone, email, password, gender);
        coachID=0;
        gymID=0;
    }
    public Trainer(){
        super();
        coachID=0;
        gymID=0;
    }



}

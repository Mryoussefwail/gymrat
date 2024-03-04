package com.example.jimrat.models;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class Coach extends User{
    @Autowired
    ArrayList<Trainer>trainers;
    int gymID;

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(ArrayList<Trainer> trainers) {
        this.trainers = trainers;
    }

    public int getGymID() {
        return gymID;

    }

    public void setGymID(int gymID) {
        this.gymID = gymID;
    }

    public Coach(String name, String phone, String email, String password, String gender) {
        super(name, phone, email, password, gender);
        trainers=new ArrayList<>();
        gymID=0;
    }

    public Coach() {
        super();
        gymID=0;
        trainers=new ArrayList<>();
    }
}

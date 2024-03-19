package com.example.jimrat.models;

public class viewCoach {
    Coach coach;
    String base64Image;

    public viewCoach(Coach coach, String o) {
        this.coach=coach;
        this.base64Image=o;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}

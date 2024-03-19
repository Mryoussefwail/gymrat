package com.example.jimrat.models;

public class ViewGym {
    Gym gym;
    String base64Image;

    public ViewGym(Gym gym, String base64Image) {
        this.gym = gym;
        this.base64Image = base64Image;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}

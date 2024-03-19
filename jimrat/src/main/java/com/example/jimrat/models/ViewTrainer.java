package com.example.jimrat.models;

public class ViewTrainer {
    Trainer trainer;
    String base64Image;

    public ViewTrainer(Trainer trainer, String base64Image) {
        this.trainer = trainer;
        this.base64Image = base64Image;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}

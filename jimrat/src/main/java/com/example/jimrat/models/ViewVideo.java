package com.example.jimrat.models;

import com.example.jimrat.repositories.VideoRepository;

public class ViewVideo {
    Video video;
    String base64Image;

    public ViewVideo(Video video, String base64Image) {
        this.video = video;
        this.base64Image = base64Image;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}

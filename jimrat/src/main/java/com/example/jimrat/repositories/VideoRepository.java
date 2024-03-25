package com.example.jimrat.repositories;

import com.example.jimrat.models.Video;
import com.example.jimrat.rowmappers.VideoRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VideoRepository {
    private JdbcTemplate jdbc;
    VideoRepository(JdbcTemplate jdbcTemplate){
        this.jdbc=jdbcTemplate;
    }
    public Video getVideoByID(int id){
        String sql="select * from gymrat.video where ID = ?";
        List<Video>videos=jdbc.query(sql,new VideoRowMapper(),id);
         if (videos.size()==0){
            return null;
         }
         return videos.get(0);
    }
    public Video getVideoByFilePath(String path){
        String sql="select * from gymrat.video where filepath = ?";
        List<Video>videos=jdbc.query(sql,new VideoRowMapper(),path);
        if (videos.size()==0){
            return null;
        }
        return videos.get(0);
    }
    public List<Video> getAllVideos(){
        String sql="select * from gymrat.video ";
        List<Video>videos=jdbc.query(sql,new VideoRowMapper());
        if (videos.size()==0){
            return null;
        }
        return videos;
    }
    public List<Video> getVideosByUserID(int id){
        String sql="select * from gymrat.video where UserID = ?";
        List<Video>videos=jdbc.query(sql,new VideoRowMapper(),id);
        if (videos.size()==0){
            return null;
        }
        return videos;
    }

    public void addVideo(Video video) {
        String sql="insert into gymrat.video values (NULL ,?,?,?,?,?,?)";
        jdbc.update(sql,video.getName(),video.getFilePath(),video.getType(),video.getImageData(),video.getUserid(),video.getUsertype());
    }

}

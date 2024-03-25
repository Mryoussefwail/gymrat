package com.example.jimrat.rowmappers;

import com.example.jimrat.models.Video;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VideoRowMapper implements RowMapper {
    @Override
    public Video mapRow(ResultSet rs, int rowNum) throws SQLException {
        Video video=new Video();
        video.setId(rs.getLong("ID"));
        video.setName(rs.getString("name"));
        video.setFilePath(rs.getString("filepath"));
        video.setType(rs.getString("type"));
        video.setImageData(rs.getBytes("videoData"));
        video.setUserid(rs.getInt("UserID"));
        video.setUsertype(rs.getString("UserType"));
        return video;
    }
}

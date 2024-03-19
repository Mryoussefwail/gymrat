package com.example.jimrat.rowmappers;

import com.example.jimrat.models.Image;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageRowMapper implements RowMapper {
    @Override
    public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
        Image files=new Image();
        files.setId(rs.getLong("ID"));
        files.setFilePath(rs.getString("filePath"));
        files.setName(rs.getString("name"));
        files.setType(rs.getString("type"));
        files.setImageData(rs.getBytes("imageData"));
        return files;
    }
}

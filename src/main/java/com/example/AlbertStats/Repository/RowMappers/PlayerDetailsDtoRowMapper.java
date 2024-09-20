package com.example.AlbertStats.Repository.RowMappers;

import com.example.AlbertStats.DTOs.PlayerDetailsDTO;
import com.example.AlbertStats.Entities.Hero;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDetailsDtoRowMapper implements RowMapper<PlayerDetailsDTO> {
    @Override
    public PlayerDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        Hero hero = new Hero();
        return null;
    }
}

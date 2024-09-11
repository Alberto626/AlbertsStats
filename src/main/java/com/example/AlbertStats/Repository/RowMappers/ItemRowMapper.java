package com.example.AlbertStats.Repository.RowMappers;

import com.example.AlbertStats.Entities.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();
        item.setId(rs.getInt("id"));
        item.setDescription(rs.getString("name"));
        item.setCost(rs.getInt("cost"));
        return item;
    }
}

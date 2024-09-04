package com.example.AlbertStats.Repository.RowMappers;

import com.example.AlbertStats.Scheduler.JsonReading.Hero;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class HeroRowMapper implements RowMapper<Hero> {


    @Override
    public Hero mapRow(ResultSet rs, int rowNum) throws SQLException {
        Hero hero = new Hero();
        hero.setId(rs.getInt("id"));
        hero.setName(rs.getString("name"));
        hero.setPrimaryAttribute(rs.getString("primary_attribute"));
        List<String> roles = Arrays.asList(rs.getString("roles").split(","));//convert string to array by splitting it between the commas
        hero.setRoles(roles);
        return hero;
    }
}

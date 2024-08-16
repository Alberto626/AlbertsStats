package com.example.AlbertStats.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Dota2Repo implements BasicRepository {

    private final JdbcTemplate mySqlJdbcTemplate;
    public Dota2Repo(JdbcTemplate mySqlJdbcTemplate) {//get JDBC template from RepoConfig file
        this.mySqlJdbcTemplate = mySqlJdbcTemplate;
    }
    @Override
    public void addData() {

    }

    @Override
    public void deleteData() {

    }

    @Override
    public void getLatest() {

    }

    @Override
    public List<?> getAll() {
        return null;
    }
}

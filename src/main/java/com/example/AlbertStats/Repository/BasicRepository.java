package com.example.AlbertStats.Repository;

import java.util.List;

public interface BasicRepository {

    public void addData();
    public void deleteData();
    public void getLatest();
    public List<?> getAll();
}

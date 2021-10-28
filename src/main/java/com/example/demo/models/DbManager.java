package com.example.demo.models;

import com.example.demo.models.tables.TableMovies;

public class DbManager {
    private static DbManager instance = null;

    public static DbManager getInstance()
    {
        if(instance==null)
        {
            instance = new DbManager();
        }
        return instance;
    }

    private String URL = "jdbc:postgresql://localhost:5432/postgres?currentSchema=movies";
    private String LOGIN = "postgres";
    private String PASSWORD = "1234";

    public TableMovies tableMovies;

    public DbManager() {
        tableMovies = new TableMovies(URL, LOGIN, PASSWORD);
    }
}

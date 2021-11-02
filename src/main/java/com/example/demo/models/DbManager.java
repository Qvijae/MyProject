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

    private String URL = "jdbc:postgresql://ec2-54-216-17-9.eu-west-1.compute.amazonaws.com:5432/de9rphrmehomn0";
    private String LOGIN = "pehunnoweicslu";
    private String PASSWORD = "a905330a9a8c3e3c119b408db1e210ed67016dad93dc7465023277ca3e1e21ee";

    public TableMovies tableMovies;

    public DbManager() {
        tableMovies = new TableMovies(URL, LOGIN, PASSWORD);
    }
}

package com.example.demo.models.tables;

import com.example.demo.models.entities.Movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

public class TableMovies {
    private String url;
    private String login;
    private String password;

    private Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");

        Properties props = new Properties();
        props.setProperty("user", login);
        props.setProperty("password", password);
        props.setProperty("ssl", "false");

        return DriverManager.getConnection(url, props);
    }

    public TableMovies(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public ArrayList<Movie> getAll() throws Exception {

        Connection connection = null;

        try {
            connection = getConnection();

            Statement statement = connection.createStatement();

            String query = String.format("SELECT * FROM movies");
            ResultSet resultSet = statement.executeQuery(query);

            ArrayList<Movie> movies = new ArrayList<>();

            while (resultSet.next() == true) {
                Movie movie = new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("rating")
                );

                movies.add(movie);
            }

            return movies;
        } catch (Exception e) {
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public Movie getById(int id) throws Exception {
        Connection connection = null;

        try {
            connection = getConnection();

            Statement statement = connection.createStatement();

            String query = String.format("SELECT * FROM movies WHERE id=%d", id);
            ResultSet resultSet = statement.executeQuery(query);

            Movie movie = null;

            if (resultSet.next() == true) {
                movie = new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("rating")
                );
            } else {
                throw new Exception();
            }

            return movie;
        } catch (Exception e) {
            throw new Exception("Ошибка получения персонажа по ИД из базы данных");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }


    public void insertOne(Movie movie) throws Exception {
        Connection connection = null;
        try {
            connection = getConnection();

            Statement statement = connection.createStatement();

            String query = String.format(Locale.US, "insert into movies (name, rating) values ('%s',%f)", movie.name, movie.rating);
            statement.executeUpdate(query);

        } catch (Exception e) {
            throw new Exception("Ошибка вставки персонажа в базу данных");
        }
    }

    public void deleteById(int id) throws Exception {
        Connection connection = null;
        try {
            connection = getConnection();

            Statement statement = connection.createStatement();

            String query = String.format("delete from movies where id=%d", id);
            statement.executeUpdate(query);

        } catch (Exception e) {
            throw new Exception("Ошибка удаления фильма из базы данных");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void updateById(int id, Movie movie) throws Exception {
        Connection connection = null;
        try {
            connection = getConnection();

            Statement statement = connection.createStatement();

            String query = String.format(Locale.US, "update movies set name = '%s', rating = %f where id = %d", movie.name, movie.rating, id);
            statement.executeUpdate(query);

        } catch (Exception e) {
            throw new Exception("Ошибка обновления фильма в базе данных");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}

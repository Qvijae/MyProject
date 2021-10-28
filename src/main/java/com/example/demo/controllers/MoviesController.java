package com.example.demo.controllers;

import com.example.demo.models.DbManager;
import com.example.demo.models.entities.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/movies", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class MoviesController {

    private void checkApiKey(String apiKey) throws Exception {
        String originalApiKey = "1212";

        if (originalApiKey.equals(apiKey) == false) {
            throw new Exception("Ошибка неверный API ключ");
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("ERROR: " + exception.getMessage());
    }


    @GetMapping(value = "/getAll")
    public ArrayList<Movie> getAll(@RequestHeader("APIKEY") String apiKey) throws Exception {
        checkApiKey(apiKey);

        DbManager db = DbManager.getInstance();
        return db.tableMovies.getAll();
    }

    @GetMapping(value = "/getById/{id}")
    public Movie getById(@RequestHeader("APIKEY") String apiKey, @PathVariable int id) throws Exception {
        checkApiKey(apiKey);

        DbManager db = DbManager.getInstance();
        return db.tableMovies.getById(id);
    }

    @PostMapping(value = "/insertOne")
    public void insertOne(@RequestHeader("APIKEY") String apiKey, @RequestBody Movie movie) throws Exception {
        checkApiKey(apiKey);

        DbManager db = DbManager.getInstance();
        db.tableMovies.insertOne(movie);
    }

    @PutMapping(value = "/updateById/{id}")
    public void updateById(@RequestHeader("APIKEY") String apiKey, @PathVariable int id, @RequestBody Movie movie) throws Exception {
        checkApiKey(apiKey);

        DbManager db = DbManager.getInstance();
        db.tableMovies.getById(id);
        db.tableMovies.updateById(id, movie);
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public void deleteById(@RequestHeader("APIKEY") String apiKey, @PathVariable int id) throws Exception {
        checkApiKey(apiKey);

        DbManager db = DbManager.getInstance();
        db.tableMovies.getById(id);
        db.tableMovies.deleteById(id);
    }
    @GetMapping(value="/getHelloWorld")
    public String getHelloWorld(){
        return "hello";
    }


}

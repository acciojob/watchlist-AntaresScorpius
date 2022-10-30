package com.driver;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class MovieRespository {
    List<Movie> movieDB = new ArrayList<>();
    List<Director> directorDB = new ArrayList<>();
    HashMap<String, List<String>> directorMoviesDB = new HashMap<>();

}

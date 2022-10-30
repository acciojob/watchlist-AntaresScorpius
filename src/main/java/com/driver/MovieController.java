package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RequestMapping("/movies/")
@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie/")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        System.out.println("Add-Movies: ");
        movieService.addMovie(movie);
        movieService.printDB();
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        System.out.println("Add-Director");
        movieService.addDirector(director);
        movieService.printDB();
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
    @PutMapping("/add-movie-director-pair/")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam ("movie") String movie,
                                                       @RequestParam("director") String director){
        System.out.println("Put Pair");
        boolean movieFound = movieService.ifMovieExist(movie);
        boolean directorFound = movieService.ifDirectorExist(director);
        System.out.println("Movie: " +movie + " director: "+ director);
        if (movieFound && directorFound){
            movieService.directorMoviePair(director,movie);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        movieService.printDB();
        return new ResponseEntity<>("Movie or Director Does not Exist", HttpStatus.OK);
    }
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name){
        System.out.println("Get Movie: " +name);
        Movie movie = movieService.getMovie(name);
        if (movie == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name){
        System.out.println("Get Director: " +name);
        Director director = movieService.getDirector(name);
        if (director == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(director, HttpStatus.OK);
    }
    @GetMapping("/get-movies-by-director-name/{name}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String name){
        System.out.println("Get All Movies By Director: " +name);
        List<String> list = movieService.getAllMoviesByDirector(name);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/get-all-movies/")
    public ResponseEntity<List<String>> findAllMovies(){
        System.out.println("Get All movies");
        List<String> list = movieService.getAllMovies();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @DeleteMapping("/delete-director-by-name/")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("name") String name){
        System.out.println("Delete Director By Name: " +name);
        movieService.deleteDirector(name);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    @DeleteMapping("/delete-all-directors/")
    public ResponseEntity<String> deleteAllDirectors(){
        System.out.println("Delete All Directors");
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}

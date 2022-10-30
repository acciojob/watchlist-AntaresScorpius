package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service
public class MovieService {

    @Autowired
    MovieRespository movieRespository;

    void addMovie(Movie movie){
        movieRespository.movieDB.add(movie);
    }
    void addDirector(Director director){
        movieRespository.directorDB.add(director);
    }
    void directorMoviePair(String directorName, String movieName){
        if(movieRespository.directorMoviesDB.containsKey(directorName)){
            movieRespository.directorMoviesDB.get(directorName).add(movieName);
        }else{
            movieRespository.directorMoviesDB.put(directorName, new ArrayList<>());
            movieRespository.directorMoviesDB.get(directorName).add(movieName);
        }
    }
    Movie getMovie(String movieName){
        for (Movie movie: movieRespository.movieDB){
            if(movieName.equals(movie.getName())){
                return movie;
            }
        }
        return null;
    }
    Director getDirector(String movieName){
        for (Director director: movieRespository.directorDB){
            if(movieName.equals(director.getName())){
                return director;
            }
        }
        return null;
    }
    List<String> getAllMoviesByDirector(String name){
        if (movieRespository.directorMoviesDB.containsKey(name)){
            return movieRespository.directorMoviesDB.get(name);
        }else{
            return new ArrayList<>();
        }
    }
    List<String> getAllMovies(){
        List<String> res = new ArrayList<>();
        for (Movie movie: movieRespository.movieDB){
            res.add(movie.getName());
        }
        return res;
    }
    boolean ifMovieExist(String name){
        for (Movie movie: movieRespository.movieDB){
            if(name.equals(movie.getName())){
                return true;
            }
        }
        return false;
    }
    boolean ifDirectorExist(String name){
        for (Director director: movieRespository.directorDB){
            if(name.equals(director.getName())){
                return true;
            }
        }
        return false;
    }
    void deleteDirector(String name){
        List<String> list = movieRespository.directorMoviesDB.get(name);
        List<Integer> index = new ArrayList<>();
        for (String s : list){
            for (int i = 0; i < movieRespository.movieDB.size() ; i++){
                if (s.equals(movieRespository.movieDB.get(i).getName())){
                    index.add(i);
                    //movieRespository.movieDB.remove(i);
                }
            }
        }
        for (int i: index){
            movieRespository.movieDB.remove(i);
        }
        index = new ArrayList<>();
        for (int i = 0; i < movieRespository.directorDB.size();i++){
            if (name.equals(movieRespository.directorDB.get(i).getName())){
                index.add(i);
                //movieRespository.directorDB.remove(i);
            }
        }
        for (int i: index){
            movieRespository.directorDB.remove(i);
        }
        movieRespository.directorMoviesDB.remove(name);
    }
    void deleteAllDirectors(){
        List<String> list2 = new ArrayList<>();
        for (Director director: movieRespository.directorDB){
            list2.add(director.getName());
        }
        movieRespository.directorDB.clear();

        //List<String> list = movieRespository.directorMoviesDB.get(name);
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry: movieRespository.directorMoviesDB.entrySet()){
            list.addAll(entry.getValue());
        }

        List<Integer> index = new ArrayList<>();
        for (String s : list){
            for (int i = 0; i < movieRespository.movieDB.size() ; i++){
                if (s.equals(movieRespository.movieDB.get(i).getName())){
                    index.add(i);
                    //movieRespository.movieDB.remove(i);
                }
            }
        }
        for (int i: index){
            movieRespository.movieDB.remove(i);
        }
        movieRespository.directorMoviesDB = new HashMap<>();
    }

    void printDB(){
        System.out.println(movieRespository.movieDB);
        System.out.println(movieRespository.directorDB);
        System.out.println(movieRespository.directorMoviesDB);
    }
}

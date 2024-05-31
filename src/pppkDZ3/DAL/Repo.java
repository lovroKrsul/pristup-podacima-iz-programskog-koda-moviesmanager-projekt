/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pppkDZ3.DAL;


import java.util.List;
import pppkDZ3.Models.Director;
import pppkDZ3.Models.Movie;

/**
 *
 * @author daniel.bele
 */
public interface Repo {
    
    int addMovie(Movie data) throws Exception;
    void editMovie(Movie person) throws Exception;
    void deleteMovie(Movie person) throws Exception;
    Movie getMovieByID(int ID) throws Exception;
    Movie getMovieByTitle(String title) throws Exception;
    List<Movie> getMovies() throws Exception;
    int getMovieID(String title) throws Exception;
    
    int addDirector(Director data) throws Exception;
    void editDirector(Director person) throws Exception;
    void deleteDirector(Director person) throws Exception;
    Director getDirectorByID(int ID) throws Exception;
    Director getDirectorByName(String name) throws Exception;
    List<Director> getDirectors() throws Exception;
    
   default void release() throws Exception {}
}

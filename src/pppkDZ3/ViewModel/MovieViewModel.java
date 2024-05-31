/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pppkDZ3.ViewModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pppkDZ3.Models.Movie;

/**
 *
 * @author THEMAN
 */
public class MovieViewModel {
    private final Movie movie;
    
    public MovieViewModel (Movie movie) {
        this.movie=movie;
    }
    public  Movie getMovie()
    {
        return movie;
    }
    public IntegerProperty getMovieIDProperty()
    {
        return new SimpleIntegerProperty(movie.getMovieID());
    }
    public StringProperty getMovieTitleProperty()
    {
        return new SimpleStringProperty(movie.getTitle());
    }
     public StringProperty getMovieActorsProperty()
    {
        return new SimpleStringProperty(movie.getActors());
    }
      public StringProperty getMovieGanreProperty()
    {
        return new SimpleStringProperty(movie.getGanre());
    }
      public StringProperty getMovieDirectorProperty()
    {
        return new SimpleStringProperty(movie.getDirector().getName());
    }
      public ObjectProperty<byte[]> getMoviePictureProperty()
    {
        return new SimpleObjectProperty<>(movie.getPicture());
        
    }
    
    
}

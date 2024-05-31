/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pppkDZ3.ViewModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pppkDZ3.Models.Director;

/**
 *
 * @author THEMAN
 */
public class DirectorViewModel {
    private final Director director;
    public DirectorViewModel(Director director)
    {
        this.director=director;
    }
    public Director getDirector()
    {
        return this.director;
    }
     public IntegerProperty getDirectorIDProperty()
    {
        return new SimpleIntegerProperty(director.getDirectorID());
    }
    public StringProperty getDirectorNameProperty()
    {
        return new SimpleStringProperty(director.getName());
    }
}

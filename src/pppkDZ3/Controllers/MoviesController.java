/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pppkDZ3.Controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pppkDZ3.DAL.RepoFactory;
import pppkDZ3.Models.Director;
import pppkDZ3.Models.Movie;
import pppkDZ3.ViewModel.DirectorViewModel;
import pppkDZ3.ViewModel.MovieViewModel;
import pppkDZ3.utils.FileUtils;
import pppkDZ3.utils.ImageUtils;

/**
 * FXML Controller class
 *
 * @author THEMAN
 */
public class MoviesController implements Initializable {
 private Map<TextField,Label> validationMap;
     private ObservableList<MovieViewModel> movies=FXCollections.observableArrayList();
     private MovieViewModel selectedMovie;
     private ObservableList<DirectorViewModel> directors=FXCollections.observableArrayList();
     private DirectorViewModel selectedDirector;
     private Tab LastTab;
    @FXML
    private TabPane ContentTP;
    @FXML
    private Tab MoviesTB;
    @FXML
    private TableView<MovieViewModel> MoviesTV;
    @FXML
    private TableColumn<MovieViewModel, String> TitleTC;
    @FXML
    private TableColumn<MovieViewModel, String> ActorsTC;
    @FXML
    private TableColumn<MovieViewModel, String> MovieDirectorTC;
    @FXML
    private TableColumn<MovieViewModel, String> GanreTC;
    @FXML
    private Button MovieEditBTN;
    @FXML
    private Button MovieDeleteBTN;
    @FXML
    private Tab AddMovieTB;
    @FXML
    private ImageView AddMovieIMG;
    @FXML
    private Button MovieSaveBTN;
    @FXML
    private Button AddMovieUpdatePicBTN;
    @FXML
    private Label titleLBL;
    @FXML
    private TextField titleTF;
    @FXML
    private Label actorsLBL;
    @FXML
    private TextField actorsTF;
    @FXML
    private Label ganreLBL;
    @FXML
    private TextField ganreTF;
    @FXML
    private Label directorLBL;
    @FXML
    private TextField directorTF;
    @FXML
    private Label TitleErrorLBL;
    @FXML
    private Label ActorsErrorLBL;
    @FXML
    private Label GanreErrorLBL;
    @FXML
    private Label DirectorErrorLBL;
    @FXML
    private Label PictureErrorLBL;
    @FXML
    private Tab DirectorsTB;
    @FXML
    private TableView<DirectorViewModel> DirectorsTV;
    @FXML
    private TableColumn<DirectorViewModel, String> DirectorNameTC;
    @FXML
    private Button DirectorEditBtn;
    @FXML
    private Button DirectorDeleteBTN;
    @FXML
    private Tab AddDirectorTB;
    @FXML
    private Label DirectorNameLBL;
    @FXML
    private TextField DirectorNameTF;
    @FXML
    private Button DirectorSaveBTN;
    @FXML
    private Label DirectorNameErrorLBL;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        innitValidation();
        innitMovies();
        innitDirectors();
        innitTables();
        setupListeners();
    }    

   
    
     @FXML
    private void EditDirector(ActionEvent event) {
        
        if(DirectorsTV.getSelectionModel().getSelectedItem()!=null)
        {
           bindDirector(DirectorsTV.getSelectionModel().getSelectedItem());
           ContentTP.getSelectionModel().select(AddDirectorTB);
           LastTab=AddDirectorTB;
        }
    }

    @FXML
    private void DeleteSelectedDirector(ActionEvent event) {
        if(DirectorsTV.getSelectionModel().getSelectedItem()!=null)
        {
            directors.remove(DirectorsTV.getSelectionModel().getSelectedItem());
        }
    }
    @FXML
    private void EditMovie(ActionEvent event) {
        if(MoviesTV.getSelectionModel().getSelectedItem()!=null)
        {
           bindMovie(MoviesTV.getSelectionModel().getSelectedItem());
           ContentTP.getSelectionModel().select(AddMovieTB);
           LastTab=AddMovieTB;
        }
    }

    @FXML
    private void DeleteSelectedMovie(ActionEvent event) {
       if(MoviesTV.getSelectionModel().getSelectedItem()!=null)
        {
            movies.remove(MoviesTV.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void Upload (ActionEvent event) {
        File file=FileUtils.uploadFileDialog(titleTF.getScene().getWindow(),"jpg","jpeg","png");
        if(file!=null)
        {
            try {
                Image img=new Image(file.toURI().toString());
                AddMovieIMG.setImage(img);
                String ext=file.getName().substring(file.getName().lastIndexOf(".")+1);
                selectedMovie.getMovie().setPicture(ImageUtils.imageToByteArray(img,ext));
            } catch (IOException ex) {
                Logger.getLogger(MoviesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @FXML
    private void SaveMovie(ActionEvent event) {
        if(formValid())
        {
            selectedMovie.getMovie().setTitle(titleTF.getText().trim());
            selectedMovie.getMovie().setActors(actorsTF.getText().trim());
            selectedMovie.getMovie().setGanre(ganreTF.getText().trim());
            int directorID=findDirectorByName(directors,directorTF.getText().trim());
            if(directorID!=-1)
            {
                selectedMovie.getMovie().setDirector(directors.get(directorID).getDirector());
            }
           if(selectedMovie.getMovieIDProperty().get()==0)
           {
               movies.add(selectedMovie);
           }
           else
           {
                try {
                    RepoFactory.getRepository().editMovie(selectedMovie.getMovie());
                    MoviesTV.refresh();
                } catch (Exception ex) {
                    Logger.getLogger(MoviesController.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
           ContentTP.getSelectionModel().select(MoviesTB);
           resetForm();
           selectedMovie=null;

        }
    }
    
     @FXML
    private void SaveDirector(ActionEvent event) {
          if(formValid())
        {
            selectedDirector.getDirector().setName(DirectorNameTF.getText().trim());
            
        
           
           if(selectedDirector.getDirectorIDProperty().get()==0)
           {
               directors.add(selectedDirector);
           }
           else
           {
                try {
                    RepoFactory.getRepository().editDirector(selectedDirector.getDirector());
                    DirectorsTV.refresh();
                } catch (Exception ex) {
                    Logger.getLogger(MoviesController.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
           ContentTP.getSelectionModel().select(DirectorsTB);
           resetForm();
           selectedDirector=null;

        }
    }
    

    private void innitValidation() {
        validationMap=Stream.of(
        new AbstractMap.SimpleImmutableEntry<>(titleTF,TitleErrorLBL),
        new AbstractMap.SimpleImmutableEntry<>(actorsTF,ActorsErrorLBL),
        new AbstractMap.SimpleImmutableEntry<>(ganreTF,GanreErrorLBL),
        new AbstractMap.SimpleImmutableEntry<>(directorTF,DirectorErrorLBL),
        new AbstractMap.SimpleImmutableEntry<>(DirectorNameTF,DirectorNameErrorLBL)
          ).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
        
    }

    private void innitMovies() {
     try {
         RepoFactory.getRepository().getMovies().forEach(
                 m->movies.add(new MovieViewModel(m))
         );
     } catch (Exception ex) {
         Logger.getLogger(MoviesController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    private void innitDirectors() {
        try {
          
            
         RepoFactory.getRepository().getDirectors().forEach(
                 d->directors.add(new DirectorViewModel(d))
         );
     } catch (Exception ex) {
         Logger.getLogger(MoviesController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    private void innitTables() {
        TitleTC.setCellValueFactory(m->m.getValue().getMovieTitleProperty());
        ActorsTC.setCellValueFactory(m->m.getValue().getMovieActorsProperty());
        MovieDirectorTC.setCellValueFactory(m->m.getValue().getMovieDirectorProperty());
        GanreTC.setCellValueFactory(m->m.getValue().getMovieGanreProperty());
        
        DirectorNameTC.setCellValueFactory(d->d.getValue().getDirectorNameProperty());
        MoviesTV.setItems(movies);
        //ne radi iz nekog nepoznatog razloga iako bi trebalo? 
        DirectorsTV.setItems(directors);
    }

    private void setupListeners() {
        ContentTP.setOnMouseClicked(event->
        {
            if(ContentTP.getSelectionModel().getSelectedItem().equals(AddMovieTB) && !AddMovieTB.equals(LastTab))
            {
               bindMovie(null); 
            }
            if(ContentTP.getSelectionModel().getSelectedItem().equals(AddDirectorTB) && !AddDirectorTB.equals(LastTab))
            {
               bindDirector(null); 
            }
            LastTab=ContentTP.getSelectionModel().getSelectedItem();
                
        }
        );
        movies.addListener(new ListChangeListener<MovieViewModel>(){
            @Override
            public void onChanged(ListChangeListener.Change<? extends MovieViewModel> change)
            {
                if (change.next())
                {
                    if(change.wasRemoved())
                    {
                        change.getRemoved().forEach(mvm->{
                            try {
                                RepoFactory.getRepository().deleteMovie(mvm.getMovie());
                            } catch (Exception ex) {
                                Logger.getLogger(MoviesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }
                    else if(change.wasAdded())
                    {
                     change.getAddedSubList().forEach(mvm->{
                         int ID;
                         try {
                             ID = RepoFactory.getRepository().addMovie(mvm.getMovie());
                             mvm.getMovie().setMovieID(ID);
                         } catch (Exception ex) {
                             Logger.getLogger(MoviesController.class.getName()).log(Level.SEVERE, null, ex);
                         }
                         
                     });
                    }
                }
            }
        });
        
        directors.addListener(new ListChangeListener<DirectorViewModel>(){
            
             @Override
            public void onChanged(ListChangeListener.Change<? extends DirectorViewModel> change)
            {
                if (change.next())
                {
                    if(change.wasRemoved())
                    {
                        change.getRemoved().forEach(mvm->{
                            try {
                                RepoFactory.getRepository().deleteDirector(mvm.getDirector());
                            } catch (Exception ex) {
                                Logger.getLogger(MoviesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }
                    else if(change.wasAdded())
                    {
                     change.getAddedSubList().forEach(mvm->{
                         int ID;
                         try {
                             ID = RepoFactory.getRepository().addDirector(mvm.getDirector());
                             mvm.getDirector().setDirectorID(ID);
                         } catch (Exception ex) {
                             Logger.getLogger(MoviesController.class.getName()).log(Level.SEVERE, null, ex);
                         }
                         
                     });
                    }
                }
            }
        });
    }

    private void bindMovie(MovieViewModel model) {
        resetForm();
        selectedMovie=model!=null? model: new MovieViewModel(null);
        titleTF.setText(selectedMovie.getMovieTitleProperty().get());
        actorsTF.setText(selectedMovie.getMovieActorsProperty().get());
        directorTF.setText(selectedMovie.getMovieDirectorProperty().get());
        ganreTF.setText(selectedMovie.getMovieGanreProperty().get());
        AddMovieIMG.setImage(selectedMovie.getMoviePictureProperty().get()!=null?
               new Image(new ByteArrayInputStream(selectedMovie.getMoviePictureProperty().get()))
                :new Image(new File("src/Assets/no_image.png").toURI().toString())
        );
        
    }

    private void bindDirector(DirectorViewModel model) {
      resetForm();
      selectedDirector=model!=null? model: new DirectorViewModel(null);
      DirectorNameTF.setText(selectedDirector.getDirectorNameProperty().get());
      
    }

    private void resetForm() {
       validationMap.values().forEach(lb->lb.setVisible(false));
       PictureErrorLBL.setVisible(false);
       
    }

    private boolean formValid() {
        resetForm();
       final AtomicBoolean valid=new AtomicBoolean(true);
       validationMap.forEach(((tf,lbl)->{
           if(tf.getText().isEmpty())
           {
               lbl.setVisible(true);
               valid.set(false);
           }
       }));
       
       if(selectedMovie.getMoviePictureProperty().get()==null)
       {
           PictureErrorLBL.setVisible(true);
            valid.set(false);
       }
        return valid.get();
    }
   
    private int findDirectorByName(ObservableList<DirectorViewModel> list,String name)
    {
        for(DirectorViewModel d :list)
                {
                    if(d.getDirectorNameProperty().equals(name))
                    {
                        return d.getDirectorIDProperty().getValue();
                    }
                }
        
        return -1;
    }
    
}

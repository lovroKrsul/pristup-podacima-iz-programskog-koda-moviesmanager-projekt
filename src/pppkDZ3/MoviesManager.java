/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pppkDZ3;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pppkDZ3.DAL.RepoFactory;

/**
 *
 * @author THEMAN
 */
public class MoviesManager extends Application {

     @Override
    public void start(Stage primaryStage) throws IOException {
      

        Parent root = FXMLLoader.load(getClass().getResource("view/Movies.fxml"));
        
        Scene scene = new Scene(root, 600, 400);
        
        primaryStage.setTitle("Movies manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop(); //To change body of generated methods, choose Tools | Templates.
        RepoFactory.getRepository().release();
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

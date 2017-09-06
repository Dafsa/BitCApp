/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dafsa;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Dafsa
 */
public class App extends Application
{
    //Create logger
    private static final Logger logg = LogManager.getLogger(App.class);

    //Create scene
    private static Parent root = null;
    
    public void start(Stage stage) throws Exception {
        
        root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI.fxml"));

        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        logg.trace("Starting BitCApp");
        
        launch(args);
    }
}

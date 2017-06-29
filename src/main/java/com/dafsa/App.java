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
import org.apache.log4j.Logger;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javassist.Loader;

/**
 *
 * @author Dafsa
 */
public class App extends Application
{
    private static final Logger logg = Logger.getLogger(App.class);

    public static void onUpdateOrders (List<BigDecimal> orders ) {
                
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("GUI.fxml"));
        GUIController controller = loader.getController();
        controller.setLineData(orders);
        
        //Upudate the chart with JavaFX!!!!!!
        //TODO David
    }

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI.fxml"));

        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();  
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

        logg.info("Starting BitCApp");

        Runnable updateOrdersRunnable = new Runnable() {
            @Override
            public void run() {
                
                try {
                    
                    logg.info("Getting new orders....");
                    
                    List<BigDecimal> orders = BitCoinService.getOrders();
                    onUpdateOrders(orders);
                    Thread.sleep(1000 * 10);
                    
                    logg.info("New orders received.");
                    
                } catch (InterruptedException | IOException e) {
                    logg.error(e);
                }
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
                
            }
        };
        Thread ordersThread = new Thread(updateOrdersRunnable);
        ordersThread.start();
    }
}

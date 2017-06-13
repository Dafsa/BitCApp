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

/**
 *
 * @author Dafsa
 */
public class GUI extends Application {

    private static final Logger logger = Logger.getLogger(GUI.class);

    public static void onUpdateOrders (List<BigDecimal> orders ) {
        //Upudate the chart with JavaFX!!!!!!
        //TODO David
    }

    @Override
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

        logger.info("Starting BitCApp");

        Runnable updateOrdersRunnable = new Runnable() {
            @Override
            public void run() {

                //TODO Fix better stop mechanism from Java FX
                while (true) {

                    try {

                        logger.info("Getting new orders....");

                        List<BigDecimal> orders = BitCoinService.getOrders();
                        onUpdateOrders(orders);
                        Thread.sleep(1000 * 60);

                        logger.info("New orders received.");

                    } catch (InterruptedException | IOException e) {
                        logger.error(e);
                    }


                }

            }
        };


        Thread ordersThread = new Thread(updateOrdersRunnable);
        ordersThread.start();


    }
    
}

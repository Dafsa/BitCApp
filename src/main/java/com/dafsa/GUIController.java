package com.dafsa;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import java.util.logging.Level;
import javafx.application.Platform;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * FXML Controller class
 *
 * @author Dafsa
 */
public class GUIController implements Initializable {

    private static final Logger logg = LogManager.getLogger(GUIController.class);
    
    @FXML
    private AreaChart<String, String> depth_chart;
    @FXML
    private NumberAxis Depth_XAxis;
    @FXML
    private NumberAxis Depth_YAxis;
    @FXML
    private Button Ex_butt_depth;
    @FXML
    private LineChart<String, Number> spread_chart;
    @FXML
    private CategoryAxis Spread_XAxis;
    @FXML
    private NumberAxis Spread_YAxis;
    @FXML
    private Button Ex_butt_spread_click;
    
    private OrdersBean dataAll;
    
    //Button handlers
    @FXML
    private void Ex_butt_depth_click(MouseEvent event) {
    Stage stage = (Stage) Ex_butt_depth.getScene().getWindow();
    stage.close();
    System.exit(1);
    }

    @FXML
    private void Ex_butt_spread_click(MouseEvent event) {
    Stage stage = (Stage) Ex_butt_spread_click.getScene().getWindow();
    stage.close();
    System.exit(1);
    }
    
    //Logger from main App
    static org.apache.logging.log4j.Logger logger = LogManager.getLogger(App.class.getName());

    // depth chart x axis limits
    Integer depthXAxisMin = 900;
    Integer depthXAxisMax = 1500;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        logger.trace("Initializing FMX...");

        //Start a new thread
        Thread tr = new Thread(new GUIUpdater());
        tr.start();
        logger.trace("Starting new thread");
    }    
    
    class GUIUpdater implements Runnable{
        
            @Override
            public void run() {
                
                try {
                    while(true){
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run() {
                                try {
                                    //Get data 
                                    dataAll = BitCoinService.getSeries();
                                    logg.trace("Getting new data....");
                                    
                                    //Initialize Charts
                                    ChartInitSpread();
                                    ChartInitDepth();
                                    
                                    
                                    //Clear out charts
                                    depth_chart.getData().clear();
                                    spread_chart.getData().clear();

                                    //Depth chart
                                    depth_chart.getData().addAll(dataAll.getBidsDepthSeries(),dataAll.getAskDepthSeries());

                                    //Spread chart
                                    spread_chart.getData().addAll(dataAll.getBuySpreadSeries(),dataAll.getSellSpreadSeries());
                                    logg.trace("Data updated");
                                } catch (IOException ex) {
                                    java.util.logging.Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        //Update each 30 seconds
                        Thread.sleep(1000 * 30);
                        logg.trace("Refreshing...");
                    }
                } 
                catch (InterruptedException ex) {
                    logg.error(ex);
                    logg.fatal(ex);
                }
            }
    }
    public void ChartInitSpread () {
        //Chart title
        spread_chart.setTitle("Bids/Asks chart"); 
        spread_chart.setAnimated(false);
        //X axis properties
        Spread_XAxis.setLabel("Time");
        //Y axis properties
        Spread_YAxis.setLabel("EUR");
        //Set bounds
        Spread_YAxis.setLowerBound(dataAll.getMinBuySellSpread()-(dataAll.getMinBuySellSpread()*0.001));
        Spread_YAxis.setUpperBound(dataAll.getMaxBuySellSpread()+(dataAll.getMaxBuySellSpread()*0.001));
        //Disable auto ranging
        Spread_YAxis.setAutoRanging(false);
    }
    public void ChartInitDepth () {
        //Chart titel
        spread_chart.setTitle("Depth Chart");
        //X axis label
        Depth_XAxis.setLabel("EUR per BTC");
        //X axis properties
        //Set bounds
        Depth_XAxis.setLowerBound(dataAll.getMinBidsDepth());
        Depth_XAxis.setUpperBound(dataAll.getMaxAskDepth());
        Depth_XAxis.setTickUnit(80);
        //Y axis properties
        Depth_YAxis.setLabel("Volume");
        //Disable auto ranging
        Depth_XAxis.setAutoRanging(false); 
    }
}

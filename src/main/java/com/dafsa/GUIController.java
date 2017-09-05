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
    private Button Ref_butt_depth;
    @FXML
    private LineChart<String, Number> spread_chart;
    @FXML
    private CategoryAxis Spread_XAxis;
    @FXML
    private NumberAxis Spread_YAxis;
    @FXML
    private Button Ex_butt_spread_click;
    @FXML
    private Button Ref_butt_spread;
    
    private OrdersBean dataAll;
    
    //Button handlers
    @FXML
    private void Ex_butt_depth_click(MouseEvent event) {
    }

    @FXML
    private void Ref_butt_depth_click(MouseEvent event) {
    }

    @FXML
    private void Ex_butt_spread_click(MouseEvent event) {
    }

    @FXML
    private void Ref_butt_spread_click(MouseEvent event) {
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
        
        logger.info("Initializing FMX...");

        
        Thread tr = new Thread(new GUIUpdater());
        tr.start();

       
    }    
    
    
    class GUIUpdater implements Runnable{
        
            @Override
            public void run() {
                
                try {
                    
                    
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run() {
                            try {
                                //Feed charts with data
                                dataAll = BitCoinService.getSeries();
                                
                                //Initialize Charts
                                ChartInitSpread();
                                ChartInitDepth();
                                logg.info("Getting new orders....");
                                //Clear out charts
                                depth_chart.getData().clear();
                                spread_chart.getData().clear();
                                
                                //Depth chart
                                depth_chart.getData().addAll(dataAll.getBidsDepthSeries(),dataAll.getAskDepthSeries());
                                
                                //Spread chart

                                spread_chart.getData().addAll(dataAll.getBuySpreadSeries(),dataAll.getSellSpreadSeries());
                            } catch (IOException ex) {
                                java.util.logging.Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });

                    
                    
                    Thread.sleep(1000 * 10);
                    
 
                } 
                catch (InterruptedException ex) {
                    logg.error(ex);
                    logg.fatal(ex);
                }
            }
       
    }
    public void ChartInitSpread () {
        //Chart titel
        spread_chart.setTitle("Bids/Asks chart");
        
        spread_chart.setAnimated(false);
        //X axis properties
        Spread_XAxis.setLabel("Time");
        //Y axis properties
        Spread_YAxis.setLabel("EUR");
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

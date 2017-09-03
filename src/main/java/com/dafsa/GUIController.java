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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitstamp.service.BitstampMarketDataService;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.kraken.KrakenExchange;
import org.knowm.xchange.kraken.dto.marketdata.KrakenDepth;
import org.knowm.xchange.kraken.dto.marketdata.KrakenPublicOrder;
import org.knowm.xchange.kraken.dto.marketdata.KrakenPublicTrades;
import org.knowm.xchange.kraken.dto.marketdata.KrakenServerTime;
import org.knowm.xchange.kraken.dto.marketdata.KrakenSpread;
import org.knowm.xchange.kraken.dto.marketdata.KrakenSpreads;
import org.knowm.xchange.kraken.dto.trade.KrakenOrder;
import org.knowm.xchange.kraken.service.KrakenMarketDataService;
import org.knowm.xchange.service.marketdata.MarketDataService;

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
    private LineChart<?, ?> spread_chart;
    @FXML
    private NumberAxis Spread_XAxis;
    @FXML
    private NumberAxis Spread_YAxis;
    @FXML
    private Button Ex_butt_spread_click;
    @FXML
    private Button Ref_butt_spread;
    
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
        //Initialize Charts
//        ChartInitSpread();
//        ChartInitDepth();
        
        Thread tr = new Thread(new GUIUpdater());
        tr.start();

       
    }    
    
    
    class GUIUpdater implements Runnable{
        
            @Override
            public void run() {
                
                try {
                    OrdersBean dataAll = BitCoinService.getSeries();
                    logg.info("Getting new orders....");
                    
                    //Feed charts with data
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run() {
                            //Clear out charts 
                            depth_chart.getData().clear();
                            spread_chart.getData().clear();
                            
                            //Depth chart
                            depth_chart.getData().addAll(dataAll.getBidsDepthSeries(),dataAll.getAskDepthSeries());
                            
                            //Spread chart
                            
                           spread_chart.getData().addAll(dataAll.getBuySpreadSeries(),dataAll.getSellSpreadSeries());
                           throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });

                    
                    
                    Thread.sleep(1000 * 10);
                    
 
                } 
                catch (InterruptedException ex) {
                    logg.error(ex);
                    logg.fatal(ex);
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
                }
                throw new UnsupportedOperationException("Not supported yet.");
            }
       
    }
    public void ChartInitSpread () {
        //Chart titel
        spread_chart.setTitle("Spread Chart");
        //X axis label
        Spread_YAxis.setLabel("Time");
        //X axis properties
        //Show three minutes of data
        Integer spreadRange = 3*60*1000;
        //Set bounds
        Spread_XAxis.setLowerBound(new Date().getTime() - spreadRange);
        Spread_XAxis.setUpperBound(new Date().getTime());
        Spread_XAxis.setTickUnit(1*60*1000); //tick in minutes
        //Y axis properties
        Spread_YAxis.setLabel("EUR");
        //Disable auto ranging
        Spread_XAxis.setAutoRanging(false);
    }
    public void ChartInitDepth () {
        //Chart titel
        spread_chart.setTitle("Depth Chart");
        //X axis label
        Depth_XAxis.setLabel("EUR per BTC");
        //X axis properties
        //Set bounds
        Depth_XAxis.setLowerBound(depthXAxisMin);
        Depth_XAxis.setUpperBound(depthXAxisMax);
        Depth_XAxis.setTickUnit(80);
        //Y axis properties
        Depth_YAxis.setLabel("Volume");
        //Disable auto ranging
        Depth_XAxis.setAutoRanging(false); 
    }
}

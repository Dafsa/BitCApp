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
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
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

    // TO UPLOAD
    // depth chart x axis lower limit
    double depthXAxisMin = 900;
    
    // depth chart x axis upper limit
    double depthXAxisMax = 1500;
    
    // spread interval
    double spreadInterval = 5*60*1000; // in miliseconds
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        logger.info("Initializing FMX...");
        
        // TO UPLOAD
        // Customize spread chart
        spread_chart.setTitle("Spread Chart");
        Spread_XAxis.setAutoRanging(false);
        Spread_XAxis.setLowerBound(new Date().getTime() - spreadInterval);
        Spread_XAxis.setUpperBound(new Date().getTime());
        Spread_XAxis.setTickUnit(1*60*1000);
        Spread_YAxis.setLabel("USD");
        
        Spread_XAxis.setAutoRanging(false);
        
        // Customize depth chart
        depth_chart.setTitle("Depth Chart");
        Depth_XAxis.setAutoRanging(false);
        Depth_XAxis.setLowerBound(depthXAxisMin);
        Depth_XAxis.setUpperBound(depthXAxisMax);
        Depth_XAxis.setTickUnit(50);
        Depth_XAxis.setLabel("USD per BTC");
        Depth_YAxis.setLabel("Cummulative volume");
        
        Runnable updateOrdersRunnable = new Runnable() {
            @Override
            public void run() {
                
                try {
                    
                    logg.info("Getting new orders....");
                    
//                    onUpdateOrders(orders);
                    
                    Thread.sleep(1000 * 10);
                    
                    logg.info("New orders received.");
                    
                } 
                catch (InterruptedException e) {
                    logg.error(e);
                    logg.fatal(e);
                }
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        
        Thread ordersThread = new Thread(updateOrdersRunnable);
        ordersThread.start();          
    }    
    public void onUpdateOrders (List<BigDecimal> orders ) {

    //Upudate the chart with JavaFX!!!!!!
    //TODO David     
}
    
}

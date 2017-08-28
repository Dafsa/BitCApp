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
import java.util.List;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import org.apache.log4j.Logger;


/**
 * FXML Controller class
 *
 * @author Dafsa
 */
public class GUIController implements Initializable {

    private static final Logger logg = Logger.getLogger(GUIController.class);
    
    @FXML
    private AreaChart<String, String> depth_chart;
    @FXML
    private Button Ex_butt_depth;
    @FXML
    private Button Ref_butt_depth;
    @FXML
    private LineChart<?, ?> spread_chart;
    @FXML
    private Button Ex_butt_spread_click;
    @FXML
    private Button Ref_butt_spread;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
    
    public void setLineData(List<BigDecimal> orders) {
        XYChart.Series series = new XYChart.Series();
        series.setName("My Test Data");

        spread_chart.getData().add(series);
        
        int i=0;
        i++;
    }
    
    class IntegerWrapper {
        private Integer value = null;

        public IntegerWrapper (Integer value) {
            this.value = value;
        }
        
        /**
         * @return the value
         */
        public Integer getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(Integer value) {
            this.value = value;
        }             
    }
    
    public void onUpdateOrders (List<BigDecimal> orders ) {
        
    spread_chart.getData().clear();
    final XYChart.Series series = new XYChart.Series();
    series.getData().add(new XYChart.Data(1, 23));
    series.getData().add(new XYChart.Data(2, 14));
    series.getData().add(new XYChart.Data(3, 15));
    spread_chart.getData().add(series);
//        IntegerWrapper counter = new IntegerWrapper(1);
//        for (BigDecimal order : orders) {
//            
//            Platform.runLater(new Runnable() {
//                 @Override public void run() {
//                     series.getData().add(new XYChart.Data(counter.getValue(), order.intValue()));
//                     counter.setValue(counter.getValue() + 1);
//                 }
//             });
//            
//            
//        } 
//        int i = 0;
//        i++;
        
    //Upudate the chart with JavaFX!!!!!!
    //TODO David     
}
    
}

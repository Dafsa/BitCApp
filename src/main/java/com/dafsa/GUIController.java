package com.dafsa;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


/**
 * FXML Controller class
 *
 * @author Dafsa
 */
public class GUIController implements Initializable {

    @FXML
    private AreaChart<?, ?> depth_chart;
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
        // TODO
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
    
}

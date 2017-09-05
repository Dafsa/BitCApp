/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dafsa;

import javafx.scene.chart.XYChart.Series;

/**
 *
 * @author U37895
 */
public class OrdersBean {


    
    private Series buySpreadSeries = null;
    private Series sellSpreadSeries = null;
    private Series bidsDepthSeries = null;
    private Series askDepthSeries = null;

    private Double minAskDepth = null;
    private Double maxAskDepth = null;       
    private Double minBidsDepth = null;
    private Double maxBidsDepth = null; 
    private Double minBuySellSpread = null;
    private Double maxBuySellSpread = null;

    public Double getMinAskDepth() {
        return minAskDepth;
    }

    public void setMinAskDepth(Double minAskDepth) {
        this.minAskDepth = minAskDepth;
    }

    public Double getMaxAskDepth() {
        return maxAskDepth;
    }

    public void setMaxAskDepth(Double maxAskDepth) {
        this.maxAskDepth = maxAskDepth;
    }

    public Double getMinBidsDepth() {
        return minBidsDepth;
    }

    public void setMinBidsDepth(Double minBidsDepth) {
        this.minBidsDepth = minBidsDepth;
    }

    public Double getMaxBidsDepth() {
        return maxBidsDepth;
    }

    public void setMaxBidsDepth(Double maxBidsDepth) {
        this.maxBidsDepth = maxBidsDepth;
    }
            
    public Series getBuySpreadSeries() {
        return buySpreadSeries;
    }

    public void setBuySpreadSeries(Series buySpreadSeries) {
        this.buySpreadSeries = buySpreadSeries;
    }

    public Series getSellSpreadSeries() {
        return sellSpreadSeries;
    }

    public void setSellSpreadSeries(Series sellSpreadSeries) {
        this.sellSpreadSeries = sellSpreadSeries;
    }

    public Series getBidsDepthSeries() {
        return bidsDepthSeries;
    }

    public void setBidsDepthSeries(Series bidsDepthSeries) {
        this.bidsDepthSeries = bidsDepthSeries;
    }

    public Series getAskDepthSeries() {
        return askDepthSeries;
    }

    public void setAskDepthSeries(Series askDepthSeries) {
        this.askDepthSeries = askDepthSeries;
    }
            
    public Double getMinBuySellSpread() {
        return minBuySellSpread;
    }

    public void setMinBuySellSpread(Double minBuySellSpread) {
        this.minBuySellSpread = minBuySellSpread;
    }

    public Double getMaxBuySellSpread() {
        return maxBuySellSpread;
    }

    public void setMaxBuySellSpread(Double maxBuySellSpread) {
        this.maxBuySellSpread = maxBuySellSpread;
    }
}
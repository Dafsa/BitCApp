package com.dafsa;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.marketdata.MarketDataService;
import java.io.IOException;
import java.math.BigDecimal;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitstamp.BitstampExchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.kraken.KrakenExchange;
import org.knowm.xchange.kraken.dto.marketdata.KrakenDepth;
import org.knowm.xchange.kraken.dto.marketdata.KrakenPublicOrder;
import org.knowm.xchange.kraken.dto.marketdata.KrakenSpread;
import org.knowm.xchange.kraken.dto.marketdata.KrakenSpreads;
import org.knowm.xchange.kraken.service.KrakenMarketDataService;

/**
 * Demonstrate requesting OrderBook from Bitstamp and plotting it using XChart.
 */
public class BitCoinService {

    private static final CurrencyPair CURRENCY = CurrencyPair.BTC_EUR;

    public static OrdersBean getSeries() throws IOException {

        // Use the factory to get the version 1 Bitstamp exchange API using default settings
        Exchange bitstampExchange = ExchangeFactory.INSTANCE.createExchange(KrakenExchange.class.getName());

        // Interested in the public market data feed (no authentication)
        MarketDataService marketDataService = bitstampExchange.getMarketDataService();
        KrakenMarketDataService marketDataServiceRaw = (KrakenMarketDataService) marketDataService;
        
        //Spread chart
        KrakenSpreads dataSpread = marketDataServiceRaw.getKrakenSpreads(Currency.BTC, Currency.EUR);
        
       //Bid Series
        final Series buySpreadSeries = new Series();
        buySpreadSeries.setName("Buys");
        
        for(KrakenSpread data : dataSpread.getSpreads()){
            buySpreadSeries.getData().add(new XYChart.Data(data.getTime()*1000,data.getAsk()));
        }
        
        //Ask series
        final Series sellSpreadSeries = new Series();
        sellSpreadSeries.setName("Sells");
        for(KrakenSpread data : dataSpread.getSpreads()){
            sellSpreadSeries.getData().add(new XYChart.Data(data.getTime()*1000,data.getBid()));
        }
        
        //Depth chart
        KrakenDepth dataDepth = marketDataServiceRaw.getKrakenDepth(CurrencyPair.BTC_EUR, Long.MAX_VALUE);
        
        //Bids series
        final Series bidsDepthSeries = new Series();
        bidsDepthSeries.setName("Bids");
        
        BigDecimal bids = new BigDecimal("0");
        for(KrakenPublicOrder orders: dataDepth.getBids()){
            bids = bids.add(orders.getVolume());
            bidsDepthSeries.getData().add(new XYChart.Data(orders.getPrice(),bids));
        }
        
        //Ask series
        final Series askDepthSeries = new Series();
        askDepthSeries.setName("Asks");       
        BigDecimal asks = new BigDecimal("0");
        for(KrakenPublicOrder orders: dataDepth.getAsks()){
            asks = asks.add(orders.getVolume());
            askDepthSeries.getData().add(new XYChart.Data(orders.getPrice().doubleValue(),asks));
        }
        
        OrdersBean allSeries = new OrdersBean();
        allSeries.setAskDepthSeries(askDepthSeries);
        allSeries.setBidsDepthSeries(bidsDepthSeries);
        allSeries.setBuySpreadSeries(buySpreadSeries);
        allSeries.setSellSpreadSeries(sellSpreadSeries);
        
        return allSeries;
    }
}
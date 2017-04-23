package com.dafsa;

/**
 * Created by rodrigo on 4/23/17.
 */

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitstamp.BitstampExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Demonstrate requesting OrderBook from Bitstamp and plotting it using XChart.
 */
public class BitCoinService {

    private static final CurrencyPair CURRENCY = CurrencyPair.BTC_EUR;

    public static List<BigDecimal> getOrders() throws IOException {

        // Use the factory to get the version 1 Bitstamp exchange API using default settings
        Exchange bitstampExchange = ExchangeFactory.INSTANCE.createExchange(BitstampExchange.class.getName());

        // Interested in the public market data feed (no authentication)
        MarketDataService marketDataService = bitstampExchange.getMarketDataService();

        // Get the current orderbook
        OrderBook orderBook = marketDataService.getOrderBook(CURRENCY);

        List<BigDecimal> orders = orderBook.getBids().stream()
                .map( bid -> bid.getTradableAmount() )
                .collect(Collectors.toList());

        return orders;
    }
}

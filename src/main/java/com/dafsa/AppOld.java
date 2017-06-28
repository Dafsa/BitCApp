package com.dafsa;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * Main class: Entry point of the application.
 *
 * @author Dafsa
 *
 */
public class AppOld 
{

    private static final Logger logger = Logger.getLogger(AppOld.class);

    public static void onUpdateOrders (List<BigDecimal> orders ) {
        //Upudate the chart with JavaFX!!!!!!
        //TODO David
    }

    public static void main( String[] args ) {
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

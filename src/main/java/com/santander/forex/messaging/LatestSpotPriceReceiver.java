package com.santander.forex.messaging;

public interface LatestSpotPriceReceiver {

    void onMessage(String message);
}

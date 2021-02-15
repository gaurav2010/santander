package com.santander.forex.service;

import com.santander.forex.domain.SpotPrice;

public interface SpotPriceCalculatorEngine {

    void changeSpotPrice(SpotPrice spotPrice);

    SpotPrice getSpotPrice(String instrumentName);

}

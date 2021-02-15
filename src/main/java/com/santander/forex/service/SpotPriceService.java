package com.santander.forex.service;

import com.santander.forex.domain.SpotPrice;

public interface SpotPriceService {

    SpotPrice getPrice(String instrumentName);

}

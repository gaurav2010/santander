package com.santander.forex.service;


import com.santander.forex.domain.SpotPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(SpotPriceServiceImpl.BEAN_NAME)
public class SpotPriceServiceImpl implements SpotPriceService {

    private static final Logger LOG = LoggerFactory.getLogger(SpotPriceServiceImpl.class);

    public static final String BEAN_NAME = "com.santander.forex.spotPrice.service.SpotPriceService";

    @Resource(name = SpotPriceCalculatorEngineImpl.BEAN_NAME)
    SpotPriceCalculatorEngine spotPriceCalculatorEngine;

    @Override
    public SpotPrice getPrice(String instrumentName) {
        LOG.info("Service - Get Spot Price for id {}", instrumentName);
        return spotPriceCalculatorEngine.getSpotPrice(instrumentName);
    }
}

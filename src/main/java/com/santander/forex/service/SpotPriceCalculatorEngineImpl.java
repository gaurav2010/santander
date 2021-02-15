package com.santander.forex.service;

import com.santander.forex.domain.InstrumentNotFoundException;
import com.santander.forex.domain.SpotPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.santander.forex.util.ApplyCommissionUtil.applyCommission;

/**
 *I am using Concurrent HashMap over ReadWriteLock as from problem we are only publishing the last available spot price
 *I am assuming here that there is a possibility especially if the read and write is happening on the same combo where we may get a stale price
 *If there is a need to an accurate latest data - we can switch to readwrite Lock
 */
@Service(SpotPriceCalculatorEngineImpl.BEAN_NAME)
public class SpotPriceCalculatorEngineImpl implements SpotPriceCalculatorEngine {

    private static final Logger LOG = LoggerFactory.getLogger(SpotPriceCalculatorEngineImpl.class);

    public static final String BEAN_NAME = "com.santander.forex.service.SpotPriceCalculatorEngineImpl";


    //Here, I have not initialised with default parameter for ConcurrentHashMap but in real-world, we will set below
    //InitialCapacity - Since we know most combinations - We should have a size that needs no rehashing
    //LoadFactor - 75% (leave it default)
    //concurrencyLevel - Unless we plan to do multi-threading write operation which is must we can leave it 1 for now
    private final Map<String, SpotPrice> spotPrices = new ConcurrentHashMap<>();

    public void changeSpotPrice(SpotPrice spotPrice) {
        applyCommission(spotPrice);
        spotPrices.put(spotPrice.getInstrumentName(), spotPrice);
    }

    @Override
    public SpotPrice getSpotPrice(String instrumentName) {
        SpotPrice spotPrice = spotPrices.getOrDefault(instrumentName, null);
        if(spotPrice == null){
            throw new InstrumentNotFoundException(instrumentName);
        }
        return spotPrice;
    }
}

package com.santander.forex.util;

import com.santander.forex.domain.SpotPrice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SpotPriceJunitGenerator {

    public static SpotPrice generate(long id, String instrumentName){
        SpotPrice spotPrice = new SpotPrice();
        spotPrice.setId(id);
        spotPrice.setInstrumentName(instrumentName);
        spotPrice.setBid(new BigDecimal("25.0000"));
        spotPrice.setAsk(new BigDecimal("35.0000"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");
        spotPrice.setTimestamp(LocalDateTime.parse("01-06-2020 12:01:01:001", formatter));
        return spotPrice;
    }
}

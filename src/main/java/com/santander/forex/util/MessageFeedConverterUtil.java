package com.santander.forex.util;

import com.santander.forex.domain.SpotPrice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageFeedConverterUtil {

    public static SpotPrice parseCsvLine(String line){
        String[] splitMessage = line.split(",");
        SpotPrice spotPrice = new SpotPrice();
        spotPrice.setId(Long.parseLong(splitMessage[0].trim()));
        spotPrice.setInstrumentName(splitMessage[1].trim());
        spotPrice.setBid(new BigDecimal(splitMessage[2].trim()));
        spotPrice.setAsk(new BigDecimal(splitMessage[3].trim()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");
        spotPrice.setTimestamp(LocalDateTime.parse(splitMessage[4].trim(), formatter));
        return  spotPrice;
    }
}

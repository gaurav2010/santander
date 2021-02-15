package com.santander.forex.util;

import com.santander.forex.domain.SpotPrice;
import org.junit.Test;

import static com.santander.forex.util.SpotPriceJunitGenerator.generate;
import static org.assertj.core.api.Assertions.assertThat;

public class MessageFeedConverterUtilTest {

    @Test
    public void testParseCsvLine(){
        String messageLine = "106, EUR/USD, 25.0000,35.0000,01-06-2020 12:01:01:001";
        SpotPrice assertedSpotPrice = generate(106l, "EUR/USD");

        SpotPrice actualSpotPrice = MessageFeedConverterUtil.parseCsvLine(messageLine);
        assertThat(actualSpotPrice).usingRecursiveComparison().isEqualTo(assertedSpotPrice);
    }
}
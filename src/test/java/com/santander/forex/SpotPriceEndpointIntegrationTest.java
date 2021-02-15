package com.santander.forex;


import com.santander.forex.domain.SpotPrice;
import com.santander.forex.service.SpotPriceCalculatorEngine;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static com.santander.forex.util.SpotPriceJunitGenerator.generate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpotPriceEndpointIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(SpotPriceEndpointIntegrationTest.class);

    @Autowired
    private SpotPriceCalculatorEngine spotPriceCalculatorEngine;

    @LocalServerPort
    private int port;

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testSimpleEndPoint(){

        SpotPrice gbpUsd =  generate(101l, "GBP/USD");
        SpotPrice eurUsd =  generate(101l, "EUR/USD");
        spotPriceCalculatorEngine.changeSpotPrice(eurUsd);
        spotPriceCalculatorEngine.changeSpotPrice(gbpUsd);

        String url = "http://localhost:" + port + "/spotPrice?instrumentName=GBP/USD";

        SpotPrice result = this.restTemplate.getForObject(url, SpotPrice.class);
        LOG.info("Simple End Point Test Spot Price is  {}", result);

        assertThat(result).isNotNull();

        //Adjust the bid and ask price with commission
        gbpUsd.setBid(new BigDecimal("24.7500"));
        gbpUsd.setAsk(new BigDecimal("35.3500"));

        assertThat(result).usingRecursiveComparison().isEqualTo(gbpUsd);
    }

    @Test
    public void testWhenPriceChangedBetweenCalls(){

        SpotPrice gbpUsd =  generate(101l, "GBP/USD");
        SpotPrice eurUsd =  generate(101l, "EUR/USD");
        spotPriceCalculatorEngine.changeSpotPrice(eurUsd);
        spotPriceCalculatorEngine.changeSpotPrice(gbpUsd);

        String url = "http://localhost:" + port + "spotPrice?instrumentName=GBP/USD";

        SpotPrice result = this.restTemplate.getForObject(url, SpotPrice.class);
        LOG.info("First End Point Test Spot Price is  {}", result);

        assertThat(result).isNotNull();

        //Changed the price between 2 similar call
        gbpUsd.setBid(new BigDecimal("30.0000"));
        gbpUsd.setAsk(new BigDecimal("40.0000"));
        spotPriceCalculatorEngine.changeSpotPrice(gbpUsd);

        SpotPrice changedResult = this.restTemplate.getForObject(url, SpotPrice.class);
        LOG.info("Subsequent End Point Test Spot Price is  {}", changedResult);
        assertThat(changedResult).isNotNull();

        //Change price as per commission
        gbpUsd.setBid(new BigDecimal("29.7000"));
        gbpUsd.setAsk(new BigDecimal("40.4000"));

        assertThat(changedResult).usingRecursiveComparison().isEqualTo(gbpUsd);
    }

}

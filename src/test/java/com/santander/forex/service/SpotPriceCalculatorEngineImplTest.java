package com.santander.forex.service;

import com.santander.forex.domain.InstrumentNotFoundException;
import com.santander.forex.domain.SpotPrice;
import com.santander.forex.util.SpotPriceJunitGenerator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SpotPriceCalculatorEngineImplTest {

    List<SpotPrice> spotPrices = new ArrayList<>();

    SpotPriceCalculatorEngine spotPriceCalculatorEngine;

    @Before
    public void setup(){
        spotPrices.add(SpotPriceJunitGenerator.generate(101l, "GBP/USD"));
        spotPrices.add(SpotPriceJunitGenerator.generate(102l, "GBP/EUR"));
        spotPriceCalculatorEngine = new SpotPriceCalculatorEngineImpl();

        spotPriceCalculatorEngine.changeSpotPrice(spotPrices.get(0));
        spotPriceCalculatorEngine.changeSpotPrice(spotPrices.get(1));

    }

    @Test
    public void getSpotPriceSuccess(){
        SpotPrice actualSpotPrice = spotPriceCalculatorEngine.getSpotPrice("GBP/USD");
        assertThat(actualSpotPrice).usingRecursiveComparison().isEqualTo(spotPrices.get(0));
    }

    @Test
    public void getSpotPriceInsrumentMissingException(){

        assertThatThrownBy(() -> {
            SpotPrice actualSpotPrice = spotPriceCalculatorEngine.getSpotPrice("EUR/USD");
        }).isInstanceOf(InstrumentNotFoundException.class).hasMessageContaining("EUR/USD");
    }
}
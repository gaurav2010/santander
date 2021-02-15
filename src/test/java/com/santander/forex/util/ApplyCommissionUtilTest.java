package com.santander.forex.util;

import com.santander.forex.domain.SpotPrice;
import org.junit.Test;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplyCommissionUtilTest {

    @Test
    public void testApplyCommission(){
        SpotPrice spotPrice = SpotPriceJunitGenerator.generate(106l, "GBP/EUR");

        ApplyCommissionUtil.applyCommission(spotPrice);
        assertThat(spotPrice.getBid().setScale(2, HALF_UP)).isEqualTo(new BigDecimal(24.75).setScale(2, HALF_UP));
        assertThat(spotPrice.getAsk().setScale(2, HALF_UP)).isEqualTo(new BigDecimal(35.35).setScale(2, HALF_UP));
    }
}
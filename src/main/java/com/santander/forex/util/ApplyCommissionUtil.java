package com.santander.forex.util;

import com.santander.forex.domain.SpotPrice;

import java.math.BigDecimal;

public class ApplyCommissionUtil {

    public static void applyCommission(SpotPrice spotPrice) {
        //Commission is hard-coded to 1%. we can make it configurable using property file if it is constant
        //It is possible that Priority client get better rates in which case we can use algorithm for same
        spotPrice.setBid(calculateAndApplyCommission(spotPrice.getBid(), new BigDecimal(-0.01)));
        spotPrice.setAsk(calculateAndApplyCommission(spotPrice.getAsk(), new BigDecimal(0.01)));
    }

    private static BigDecimal calculateAndApplyCommission(BigDecimal price, BigDecimal commission) {
        return price.add(price.multiply(commission));
    }

}

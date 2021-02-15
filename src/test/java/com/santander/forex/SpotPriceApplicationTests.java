package com.santander.forex;

import com.santander.forex.controller.SpotPriceController;
import com.santander.forex.domain.SpotPrice;
import com.santander.forex.service.SpotPriceCalculatorEngine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static com.santander.forex.util.SpotPriceJunitGenerator.generate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpotPriceApplicationTests {

	@Autowired
	private SpotPriceController controller;

	@Autowired
	private SpotPriceCalculatorEngine spotPriceCalculatorEngine;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
		assertThat(spotPriceCalculatorEngine).isNotNull();
	}

	@Test
	public void checkSpotPrice(){
		SpotPrice gbpUsd =  generate(101l, "GBP/USD");
		SpotPrice eurUsd =  generate(101l, "EUR/USD");
		spotPriceCalculatorEngine.changeSpotPrice(eurUsd);
		spotPriceCalculatorEngine.changeSpotPrice(gbpUsd);
		ResponseEntity<SpotPrice> responseEntity = controller.getSpotPrice("EUR/USD");
		assertThat(responseEntity).isNotNull();
		eurUsd.setBid(new BigDecimal("24.7500"));
		eurUsd.setAsk(new BigDecimal("35.3500"));

		assertThat(responseEntity.getBody()).usingRecursiveComparison().isEqualTo(eurUsd);
	}
}

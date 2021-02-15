package com.santander.forex.controller;

import com.santander.forex.domain.SpotPrice;
import com.santander.forex.service.SpotPriceService;
import com.santander.forex.service.SpotPriceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class SpotPriceController {

    private static final Logger LOG = LoggerFactory.getLogger(SpotPriceController.class);

    @Resource(name = SpotPriceServiceImpl.BEAN_NAME)
    private SpotPriceService spotPriceService;

    @GetMapping("spotPrice")
    public ResponseEntity<SpotPrice> getSpotPrice(@RequestParam String instrumentName){
        LOG.info("Controller - Get Spot Price for instrumentName {}", instrumentName);
        SpotPrice spotPrice = spotPriceService.getPrice(instrumentName);
        return ResponseEntity.status(OK).body(spotPrice);
    }
}

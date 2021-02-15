package com.santander.forex.domain;

public class InstrumentNotFoundException extends RuntimeException{

    public InstrumentNotFoundException(String instrumentName){
        super("Missing Spot Price for Instrument " + instrumentName);
    }
}

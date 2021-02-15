package com.santander.forex.controller;

import com.santander.forex.domain.InstrumentNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class SpotPriceErrorAdvice {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handle(RuntimeException re){
        return ResponseEntity.status(NOT_FOUND).body(re.getMessage());
    }

    @ExceptionHandler({InstrumentNotFoundException.class})
    public ResponseEntity<String> handle(InstrumentNotFoundException infe){
        return ResponseEntity.status(NOT_FOUND).body(infe.getMessage());
    }
}

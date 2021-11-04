package com.antra.restapipractice.controller;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antra.restapipractice.exception.CountryNotFoundException;
import com.antra.restapipractice.service.PersonService;
import com.antra.restapipractice.vo.CountryPredict;
import com.antra.restapipractice.vo.ErrorMessage;

@Validated
@RestController
@RequestMapping("/api/country")
public class CountryController {

    private static final Logger log = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private PersonService service;

    @GetMapping
    public ResponseEntity<CountryPredict> getCountryByName(@Pattern(regexp = "[a-zA-Z]+") @RequestParam String name)
            throws CountryNotFoundException {
        CountryPredict predict = service.findCountryByName(name);
        if (predict.getCountry() == null)
            throw new CountryNotFoundException(name);
        ResponseEntity<CountryPredict> response = new ResponseEntity<CountryPredict>(predict, HttpStatus.OK);
        return response;
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleCountNotFoundException(CountryNotFoundException e) {
        String msg = "Country not found for name " + e.getMessage();
        log.error(msg);
        ResponseEntity<ErrorMessage> response = new ResponseEntity<ErrorMessage>(
                new ErrorMessage(HttpStatus.BAD_REQUEST.value(), msg), HttpStatus.BAD_REQUEST);
        return response;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(ConstraintViolationException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Invalid parameters.");
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
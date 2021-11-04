package com.antra.restapipractice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antra.restapipractice.exception.CatFactNotFoundException;
import com.antra.restapipractice.service.CatFactService;
import com.antra.restapipractice.vo.CatFact;
import com.antra.restapipractice.vo.ErrorMessage;

@RestController
@RequestMapping("/api")
public class CatController {

    private static final Logger log = LoggerFactory.getLogger(CatController.class);

    @Autowired
    private CatFactService service;

    @GetMapping("/catFact")
    public ResponseEntity<CatFact> getRandomFact() throws CatFactNotFoundException { 
        CatFact catFact = service.findCatFact();
        if (catFact == null)
            throw new CatFactNotFoundException();
        return new ResponseEntity<CatFact>(catFact, HttpStatus.OK);
    }

    @ExceptionHandler(CatFactNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleCatFactNotFoundException() {
        String msg = "Cat fact isn't found";
        log.error(msg);
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), msg);
        ResponseEntity<ErrorMessage> response = new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
        return response;
    }
}

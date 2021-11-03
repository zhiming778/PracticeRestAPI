package com.antra.restapipractice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CatController {

    @GetMapping("/catFact")
    public ResponseEntity<?> getRandomFact() {
        return null;
    }
}

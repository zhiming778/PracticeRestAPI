package com.antra.restapipractice;

import com.antra.restapipractice.pojo.CountryPredict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    @GetMapping
    public ResponseEntity<CountryPredict> getCountryByName(@RequestParam String name) {   // TODO predict country by name from "https://api.nationalize.io?name=nathaniel"
        CountryPredict dummy = new CountryPredict();
        dummy.setName(name);
        dummy.setCountry("USA");
        return ResponseEntity.ok(dummy);
    }
}

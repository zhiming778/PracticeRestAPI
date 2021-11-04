package com.antra.restapipractice.service;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.antra.restapipractice.vo.CountryPredict;

@SpringBootTest
public class TestPersonService {

    @Autowired
    PersonService service;
    
    @Test
    public void testResponse() {
        CountryPredict predict =  service.findCountryByName("Natasha");
        assertNull(predict);
    }
}

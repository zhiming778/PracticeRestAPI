package com.antra.restapipractice.service;

import com.antra.restapipractice.vo.CountryPredict;

public interface PersonService{
    
    public CountryPredict findCountryByName(String name);
    
}

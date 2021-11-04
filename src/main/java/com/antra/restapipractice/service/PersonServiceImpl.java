package com.antra.restapipractice.service;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.antra.restapipractice.entity.PersonEntity;
import com.antra.restapipractice.repository.PersonEntityRepository;
import com.antra.restapipractice.vo.CountryPredict;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonEntityRepository repository;

    private String url = "https://api.nationalize.io";

    @Transactional
    @Cacheable(key = "#name", value = "countryCache", sync = true)
    @Override
    public CountryPredict findCountryByName(String name) {
        PersonEntity entity = repository.findByName(name);
        CountryPredict predict = new CountryPredict();
        if (entity != null) {
            predict.setCountry(entity.getCountry());
            predict.setName(entity.getName());
            return predict;
        }
        entity = new PersonEntity();
        URI uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("name", name).build().toUri();
        RestTemplate rt = new RestTemplate();
        Map<String, ?> response = (Map<String, ?>) rt.getForObject(uri, Map.class);
        predict.setName((String) response.get("name"));
        entity.setName((String) response.get("name"));
        List<Map<String, String>> countries = ((List<Map<String, String>>) response.get("country"));
        if (countries.size() > 0) {
            predict.setCountry(countries.get(0).get("country_id"));
            entity.setCountry(countries.get(0).get("country_id"));
        }
        repository.save(entity);
        return predict;
    }

}

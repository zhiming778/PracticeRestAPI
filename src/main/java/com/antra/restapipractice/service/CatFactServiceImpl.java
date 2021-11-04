package com.antra.restapipractice.service;

import java.net.URI;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.antra.restapipractice.entity.CatFactEntity;
import com.antra.restapipractice.repository.CatFactEntityRepository;
import com.antra.restapipractice.util.CatFactConverter;
import com.antra.restapipractice.vo.CatFact;

@Service
public class CatFactServiceImpl implements CatFactService {
    private String url = "https://catfact.ninja/fact";
    @Autowired
    private CatFactEntityRepository repository;

    @Transactional
    @Override
    public CatFact findCatFact() {
        URI uri = UriComponentsBuilder.fromHttpUrl(url).build().toUri();
        RestTemplate rt = new RestTemplate();
        ResponseEntity<CatFactEntity> response = rt.getForEntity(uri, CatFactEntity.class);
        if (!response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            repository.save(response.getBody());
            return CatFactConverter.toCatFact(response.getBody());
        }
        int count = (int) repository.count();
        Random random = new Random();
        long r = random.nextInt(count) + 1;
        return CatFactConverter.toCatFact(repository.findById(r).orElse(null));
    }

}

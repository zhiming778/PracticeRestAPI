package com.antra.restapipractice;

import com.antra.restapipractice.pojo.CatFact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CatController {

    @GetMapping("/catFact")
    public ResponseEntity<?> getRandomFact() {  // TODO get random fact from "https://catfact.ninja/fact"
        CatFact ret = new CatFact();
        ret.setCatFact("this is a dummy fact");
        return ResponseEntity.ok(ret);
    }
}

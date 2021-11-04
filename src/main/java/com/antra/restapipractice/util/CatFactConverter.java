package com.antra.restapipractice.util;

import com.antra.restapipractice.entity.CatFactEntity;
import com.antra.restapipractice.vo.CatFact;

public class CatFactConverter {
    public static CatFact toCatFact(CatFactEntity entity) {
        if (entity == null)
            return null;
        CatFact catFact = new CatFact();
        catFact.setCatFact(entity.getFact());
        return catFact;
    }
}

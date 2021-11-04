package com.antra.restapipractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antra.restapipractice.entity.CatFactEntity;

@Repository
public interface CatFactEntityRepository extends JpaRepository<CatFactEntity, Long> {
}

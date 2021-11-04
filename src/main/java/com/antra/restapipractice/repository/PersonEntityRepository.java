package com.antra.restapipractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antra.restapipractice.entity.PersonEntity;

@Repository
public interface PersonEntityRepository extends JpaRepository<PersonEntity, Long> {
    PersonEntity findByName(String name);
}

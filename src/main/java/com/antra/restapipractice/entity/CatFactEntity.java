package com.antra.restapipractice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity(name = "CatFact")
public class CatFactEntity {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String fact;
    @Column
    private Integer length;

    public CatFactEntity() {
    }

    public CatFactEntity(String fact, Integer length) {
        this.fact = fact;
        this.length = length;
    }

}

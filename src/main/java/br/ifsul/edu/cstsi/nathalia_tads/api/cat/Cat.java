package br.ifsul.edu.cstsi.nathalia_tads.api.cat;

import jakarta.persistence.Entity;
import jakarta.persistence.*;


@Entity
public class Cat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;
}

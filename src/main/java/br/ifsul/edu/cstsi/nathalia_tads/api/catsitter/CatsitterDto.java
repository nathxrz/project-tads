package br.ifsul.edu.cstsi.nathalia_tads.api.catsitter;

import java.time.LocalDate;

public record CatsitterDto (Long id, String name, LocalDate birthday, String gender, String phone, String email) {
    public CatsitterDto(Catsitter catsitter){
        this(catsitter.getId(), catsitter.getName(), catsitter.getBirthday(), catsitter.getGender(), catsitter.getPhone(), catsitter.getEmail());
    }
}
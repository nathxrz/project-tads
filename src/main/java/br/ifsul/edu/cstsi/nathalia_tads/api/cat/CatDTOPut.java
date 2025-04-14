package br.ifsul.edu.cstsi.nathalia_tads.api.cat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CatDTOPut (
    @NotBlank(message = "O nome não pode ser vazio")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
    String name,
    @NotBlank(message = "a raça não pode ser vazia")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
    String breed,
    @NotNull(message = "O id do tutor não pode ser nulo")
    Long tutorId
){
    public CatDTOPut(Cat cat) {
        this(cat.getName(), cat.getBreed(), cat.getTutor().getId());
    }
}

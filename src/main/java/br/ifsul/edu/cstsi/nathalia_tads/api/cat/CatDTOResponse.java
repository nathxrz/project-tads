package br.ifsul.edu.cstsi.nathalia_tads.api.cat;

public record CatDTOResponse (
    Long id,
    String name,
    String breed,
    Long tutor_id
){
    public CatDTOResponse(Cat cat){
        this(cat.getId(), cat.getName(), cat.getBreed(), cat.getTutor().getId());
    }
}

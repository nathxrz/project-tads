package br.ifsul.edu.cstsi.nathalia_tads.api.cat;
import java.io.Serializable;

public record CatDto (Long id, String name, String breed)  implements Serializable {
    public CatDto(Cat cat){
        this(cat.getId(), cat.getName(), cat.getBreed());
    }
}
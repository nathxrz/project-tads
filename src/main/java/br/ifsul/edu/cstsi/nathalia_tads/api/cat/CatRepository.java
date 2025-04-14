package br.ifsul.edu.cstsi.nathalia_tads.api.cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface CatRepository extends JpaRepository<Cat, Long>{
    List<Cat> findCatByName(String name);
}
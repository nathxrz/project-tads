package br.ifsul.edu.cstsi.nathalia_tads.api.catsitter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface CatsitterRepository extends JpaRepository<Catsitter, Long>{
}
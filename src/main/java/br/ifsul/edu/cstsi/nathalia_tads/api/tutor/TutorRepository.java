package br.ifsul.edu.cstsi.nathalia_tads.api.tutor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface TutorRepository extends JpaRepository<Tutor, Long>{
}
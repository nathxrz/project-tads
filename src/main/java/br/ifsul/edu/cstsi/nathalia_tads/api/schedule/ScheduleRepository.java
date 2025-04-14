package br.ifsul.edu.cstsi.nathalia_tads.api.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
    List<Schedule> findScheduleById(Long id);
}
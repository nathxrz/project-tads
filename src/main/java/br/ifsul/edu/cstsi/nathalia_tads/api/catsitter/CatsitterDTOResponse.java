package br.ifsul.edu.cstsi.nathalia_tads.api.catsitter;

import br.ifsul.edu.cstsi.nathalia_tads.api.schedule.Schedule;

import java.time.LocalDate;
import java.util.List;

public record CatsitterDTOResponse (
        Long id,
        String name,
        LocalDate birthday,
        String gender,
        String phone,
        String email,
        List<Schedule> schedules
){
  public CatsitterDTOResponse(Catsitter catsitter){
    this(catsitter.getId(), catsitter.getName(), catsitter.getBirthday(), catsitter.getGender(), catsitter.getPhone(), catsitter.getEmail(), catsitter.getSchedules());
  }
}
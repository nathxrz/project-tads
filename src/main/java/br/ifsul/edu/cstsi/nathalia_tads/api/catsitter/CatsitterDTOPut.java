package br.ifsul.edu.cstsi.nathalia_tads.api.catsitter;

import br.ifsul.edu.cstsi.nathalia_tads.api.schedule.Schedule;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record CatsitterDTOPut(
    @NotBlank(message = "O nome não pode ser vazio")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
    String name,
    LocalDate birthday,
    String phone,
    String gender,
    @NotBlank(message = "O e-mail não pode estar vazio")
    @Size(min = 3, max = 50, message = "O e-mail deve ter entre 2 e 20 caracteres")
    String email,
    List<Schedule> schedules
){
    public CatsitterDTOPut(Catsitter catsitter) {
        this(catsitter.getName(), catsitter.getBirthday(), catsitter.getPhone(), catsitter.getGender(), catsitter.getEmail(), catsitter.getSchedules());
    }
}
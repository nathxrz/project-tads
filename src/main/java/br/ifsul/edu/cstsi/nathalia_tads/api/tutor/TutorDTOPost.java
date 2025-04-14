package br.ifsul.edu.cstsi.nathalia_tads.api.tutor;

import br.ifsul.edu.cstsi.nathalia_tads.api.cat.Cat;
import br.ifsul.edu.cstsi.nathalia_tads.api.schedule.Schedule;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record TutorDTOPost(
        @NotBlank(message = "O nome não pode ser vazio")
        @Size(min = 3, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
        String name,
        LocalDateTime birthday,
        String phone,
        String gender,
        @NotBlank(message = "O e-mail não pode estar vazio")
        @Size(min = 3, max = 50, message = "O e-mail deve ter entre 2 e 20 caracteres")
        String email,
        String address,
        List<Cat> cats,
        List<Schedule> schedules
){
    public TutorDTOPost(Tutor tutor) {
        this(tutor.getName(), tutor.getBirthday(), tutor.getPhone(), tutor.getGender(), tutor.getEmail(), tutor.getAddress(), tutor.getCats(), tutor.getSchedules());
    }
}
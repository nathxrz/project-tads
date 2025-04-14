package br.ifsul.edu.cstsi.nathalia_tads.api.tutor;

import br.ifsul.edu.cstsi.nathalia_tads.api.cat.Cat;
import br.ifsul.edu.cstsi.nathalia_tads.api.schedule.Schedule;

import java.time.LocalDateTime;
import java.util.List;

public record TutorDTOResponse (
        Long id,
        String name,
        LocalDateTime birthday,
        String gender,
        String phone,
        String email,
        String address,
        List<Cat> cats,
        List<Schedule> schedules
){
    public TutorDTOResponse(Tutor tutor){
        this(tutor.getId(), tutor.getName(), tutor.getBirthday(), tutor.getGender(), tutor.getPhone(), tutor.getEmail(), tutor.getAddress(), tutor.getCats(), tutor.getSchedules());
    }
}
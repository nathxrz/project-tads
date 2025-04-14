package br.ifsul.edu.cstsi.nathalia_tads.api.schedule;

import java.time.LocalDateTime;

public record ScheduleDTOResponse (
        Long id,
        LocalDateTime dateTimeStart,
        LocalDateTime dateTimeEnd,
        String status,
        Long tutor_id,
        Long catsitter_id
){
    public ScheduleDTOResponse(Schedule schedule){
        this(schedule.getId(), schedule.getDateTimeStart(), schedule.getDateTimeEnd(), schedule.getStatus(), schedule.getTutor().getId(), schedule.getCatsitter().getId());
    }
}

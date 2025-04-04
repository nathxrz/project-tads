package br.ifsul.edu.cstsi.nathalia_tads.api.schedule;

import br.ifsul.edu.cstsi.nathalia_tads.api.cat.Cat;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ScheduleDto (Long id, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, String Status)  implements Serializable {
    public ScheduleDto(Schedule schedule){
        this(schedule.getId(), schedule.getDateTimeStart(), schedule.getDateTimeEnd(), schedule.getStatus());
    }
}
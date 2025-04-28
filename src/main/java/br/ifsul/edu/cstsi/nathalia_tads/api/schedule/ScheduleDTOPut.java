package br.ifsul.edu.cstsi.nathalia_tads.api.schedule;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record ScheduleDTOPut(
        @NotBlank(message = "A data/hora de início não pode ser vazia")
        LocalDateTime dateTimeStart,
        @NotBlank(message = "A data/hora de término não pode ser vazia")
        LocalDateTime dateTimeEnd,
        String status
){
    public ScheduleDTOPut(Schedule schedule) {
        this(schedule.getDateTimeStart(), schedule.getDateTimeEnd(), schedule.getStatus());
    }
}
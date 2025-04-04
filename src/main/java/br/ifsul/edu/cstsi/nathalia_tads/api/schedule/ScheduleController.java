package br.ifsul.edu.cstsi.nathalia_tads.api.schedule;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/schedules")
public class ScheduleController {
    private final ScheduleRepository scheduleRepository;

    public ScheduleController(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDto>> getSchedules() {
        var schedules = scheduleRepository.findAll();
        return ResponseEntity.ok()
                .body(
                        schedules.stream()
                                .map(schedule ->
                                        new ScheduleDto(
                                                schedule.getId(),
                                                schedule.getDateTimeStart(),
                                                schedule.getDateTimeEnd(),
                                                schedule.getStatus())).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Long id) {
        var schedule = scheduleRepository.findById(id);
        return schedule.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(schedule.get());
    }

    @GetMapping("status/{status}")
    public String getScheduleByNome(@PathVariable("status") String status) {
        return "Status: " + status;
    }
}

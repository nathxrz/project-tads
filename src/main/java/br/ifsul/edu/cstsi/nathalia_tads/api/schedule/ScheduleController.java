package br.ifsul.edu.cstsi.nathalia_tads.api.schedule;

import br.ifsul.edu.cstsi.nathalia_tads.api.catsitter.CatsitterRepository;
import br.ifsul.edu.cstsi.nathalia_tads.api.tutor.TutorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/schedules")
public class ScheduleController {
    private final ScheduleRepository scheduleRepository;
    private final TutorRepository tutorRepository;
    private final CatsitterRepository catsitterRepository;

    public ScheduleController(ScheduleRepository scheduleRepository,
                              TutorRepository tutorRepository,
                              CatsitterRepository catsitterRepository) {
        this.scheduleRepository = scheduleRepository;
        this.tutorRepository = tutorRepository;
        this.catsitterRepository = catsitterRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<ScheduleDTOResponse> getScheduleById(@PathVariable Long id) {
        var schedule = scheduleRepository.findById(id);
        if (schedule.isPresent()) {
            return ResponseEntity.ok(new ScheduleDTOResponse(schedule.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<URI> insert(@RequestBody ScheduleDTOPost scheduleDTO, UriComponentsBuilder uriBuilder) {
        var tutor = tutorRepository.findById(scheduleDTO.tutor_id());

        if(tutor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var catsitter = catsitterRepository.findById(scheduleDTO.catsitter_id());

        if(catsitter.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var schedule = scheduleRepository.save(new Schedule(
                null,
                scheduleDTO.dateTimeStart(),
                scheduleDTO.dateTimeEnd(),
                scheduleDTO.status(),
                tutor.get(),
                catsitter.get()
        ));
        var location = uriBuilder.path("api/v1/schedules/{id}").buildAndExpand(schedule.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ScheduleDTOResponse> update(@PathVariable("id") Long id, @RequestBody ScheduleDTOPut scheduleDTO) {
        var tutor = tutorRepository.findById(scheduleDTO.tutor_id());

        if(tutor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var catsitter = catsitterRepository.findById(scheduleDTO.catsitter_id());

        if(catsitter.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var schedule = scheduleRepository.findById(id);
        if (schedule.isPresent()) {
            var s = scheduleRepository.save(new Schedule(
                    id,
                    scheduleDTO.dateTimeStart(),
                    scheduleDTO.dateTimeEnd(),
                    scheduleDTO.status(),
                    tutor.get(),
                    catsitter.get()
            ));
            return ResponseEntity.ok(new ScheduleDTOResponse(s));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ScheduleDTOResponse> delete(@PathVariable("id") Long id) {
        var schedule = scheduleRepository.findById(id);
        if (schedule.isPresent()) {
            scheduleRepository.deleteById(id);
            return ResponseEntity.ok(new ScheduleDTOResponse(schedule.get()));
        }
        return ResponseEntity.notFound().build();
    }
}

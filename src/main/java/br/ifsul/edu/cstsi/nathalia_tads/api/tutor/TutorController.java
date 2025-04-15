package br.ifsul.edu.cstsi.nathalia_tads.api.tutor;

import br.ifsul.edu.cstsi.nathalia_tads.api.cat.Cat;
import br.ifsul.edu.cstsi.nathalia_tads.api.cat.CatRepository;
import br.ifsul.edu.cstsi.nathalia_tads.api.catsitter.*;
import br.ifsul.edu.cstsi.nathalia_tads.api.schedule.Schedule;
import br.ifsul.edu.cstsi.nathalia_tads.api.schedule.ScheduleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/tutors")
public class TutorController {
    private final TutorRepository tutorRepository;
    private final CatRepository catRepository;
    private final ScheduleRepository scheduleRepository;


    public TutorController(TutorRepository tutorRepository,
                           CatRepository catRepository,
                           ScheduleRepository scheduleRepository) {
        this.tutorRepository = tutorRepository;
        this.catRepository = catRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @GetMapping
    public ResponseEntity<Page<TutorDTOResponse>> findAll(@PageableDefault(size = 50, sort = "name") Pageable paginacao) {
        return ResponseEntity.ok(tutorRepository.findAll(paginacao).map(TutorDTOResponse::new));
    }

    @GetMapping("{id}")
    public ResponseEntity<TutorDTOResponse> getCatById(@PathVariable Long id) {
        var tutor = tutorRepository.findById(id);
        if (tutor.isPresent()) {
            return ResponseEntity.ok(new TutorDTOResponse(tutor.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("nome/{name}")
    public ResponseEntity<List<TutorDTOResponse>> finByNome(@PathVariable("name") String name) {
        var tutors = tutorRepository.findTutorByName(name + "%");
        return tutors.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(tutors.stream().map(TutorDTOResponse::new).toList());
    }
    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<URI> insert(@RequestBody TutorDTOPost tutorDTO, UriComponentsBuilder uriBuilder) {
        List<Long> scheduleIds = tutorDTO.schedules().stream()
                .map(Schedule::getId)
                .collect(Collectors.toList());

        List<Schedule> schedules = scheduleRepository.findAllById(scheduleIds);

        if(schedules.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Long> catsIds = tutorDTO.cats().stream()
                .map(Cat::getId)
                .collect(Collectors.toList());

        List<Cat> cats = catRepository.findAllById(catsIds);

        if(cats.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var tutor = tutorRepository.save(new Tutor(
                null,
                tutorDTO.name(),
                tutorDTO.birthday(),
                tutorDTO.phone(),
                tutorDTO.gender(),
                tutorDTO.email(),
                tutorDTO.address(),
                cats,
                schedules
        ));
        var location = uriBuilder.path("api/v1/tutors/{id}").buildAndExpand(tutor.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<TutorDTOResponse> update(@PathVariable("id") Long id, @RequestBody TutorDTOPut tutorDTO) {
        List<Long> scheduleIds = tutorDTO.schedules().stream()
                .map(Schedule::getId)
                .collect(Collectors.toList());

        List<Schedule> schedules = scheduleRepository.findAllById(scheduleIds);

        if(schedules.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Long> catsIds = tutorDTO.cats().stream()
                .map(Cat::getId)
                .collect(Collectors.toList());

        List<Cat> cats = catRepository.findAllById(catsIds);

        if(cats.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var tutor = tutorRepository.findById(id);
        if (tutor.isPresent()) {
            var c = tutorRepository.save(new Tutor(
                    id,
                    tutorDTO.name(),
                    tutorDTO.birthday(),
                    tutorDTO.phone(),
                    tutorDTO.gender(),
                    tutorDTO.email(),
                    tutorDTO.address(),
                    cats,
                    schedules
            ));
            return ResponseEntity.ok(new TutorDTOResponse(c));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TutorDTOResponse> delete(@PathVariable("id") Long id) {
        var tutor = tutorRepository.findById(id);
        if (tutor.isPresent()) {
            tutorRepository.deleteById(id);
            return ResponseEntity.ok(new TutorDTOResponse(tutor.get()));
        }
        return ResponseEntity.notFound().build();
    }
}

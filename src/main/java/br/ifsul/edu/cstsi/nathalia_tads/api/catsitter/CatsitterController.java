package br.ifsul.edu.cstsi.nathalia_tads.api.catsitter;

import br.ifsul.edu.cstsi.nathalia_tads.api.schedule.Schedule;
import br.ifsul.edu.cstsi.nathalia_tads.api.schedule.ScheduleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/catsitters")
public class CatsitterController {
    private final CatsitterRepository catsitterRepository;
    private final ScheduleRepository scheduleRepository;

    public CatsitterController(CatsitterRepository catsitterRepository, ScheduleRepository scheduleRepository) {
        this.catsitterRepository = catsitterRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @GetMapping
    public ResponseEntity<Page<CatsitterDTOResponse>> findAll(@PageableDefault(size = 50, sort = "name") Pageable paginacao) {
        return ResponseEntity.ok(catsitterRepository.findAll(paginacao).map(CatsitterDTOResponse::new));
    }

    @GetMapping("{id}")
    public ResponseEntity<CatsitterDTOResponse> getCatById(@PathVariable Long id) {
        var catsitter = catsitterRepository.findById(id);
        if (catsitter.isPresent()) {
            return ResponseEntity.ok(new CatsitterDTOResponse(catsitter.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("nome/{name}")
    public ResponseEntity<List<CatsitterDTOResponse>> finByNome(@PathVariable("name") String name) {
        var catsitters = catsitterRepository.findCatsitterByName(name + "%");
        return catsitters.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(catsitters.stream().map(CatsitterDTOResponse::new).toList());
    }
    @PostMapping
    public ResponseEntity<URI> insert(@RequestBody CatsitterDTOPost catsitterDTO, UriComponentsBuilder uriBuilder) {
        List<Long> scheduleIds = catsitterDTO.schedules().stream()
                .map(Schedule::getId)
                .collect(Collectors.toList());

        List<Schedule> schedules = scheduleRepository.findAllById(scheduleIds);

        if(schedules.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var catsitter = catsitterRepository.save(new Catsitter(
                null,
                catsitterDTO.name(),
                catsitterDTO.birthday(),
                catsitterDTO.phone(),
                catsitterDTO.gender(),
                catsitterDTO.email(),
                schedules
        ));
        var location = uriBuilder.path("api/v1/catsitters/{id}").buildAndExpand(catsitter.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<CatsitterDTOResponse> update(@PathVariable("id") Long id, @RequestBody CatsitterDTOPut catsitterDTO) {
        List<Long> scheduleIds = catsitterDTO.schedules().stream()
                .map(Schedule::getId)
                .collect(Collectors.toList());

        List<Schedule> schedules = scheduleRepository.findAllById(scheduleIds);

        if(schedules.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var catsitter = catsitterRepository.findById(id);
        if (catsitter.isPresent()) {
            var c = catsitterRepository.save(new Catsitter(
                    id,
                    catsitterDTO.name(),
                    catsitterDTO.birthday(),
                    catsitterDTO.phone(),
                    catsitterDTO.gender(),
                    catsitterDTO.email(),
                    schedules
            ));
            return ResponseEntity.ok(new CatsitterDTOResponse(c));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CatsitterDTOResponse> delete(@PathVariable("id") Long id) {
        var catsitter = catsitterRepository.findById(id);
        if (catsitter.isPresent()) {
            catsitterRepository.deleteById(id);
            return ResponseEntity.ok(new CatsitterDTOResponse(catsitter.get()));
        }
        return ResponseEntity.notFound().build();
    }
}

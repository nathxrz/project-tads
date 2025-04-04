package br.ifsul.edu.cstsi.nathalia_tads.api.tutor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/tutors")
public class TutorController {
    private final TutorRepository tutorRepository;

    public TutorController(TutorRepository tutorRepository){
        this.tutorRepository = tutorRepository;
    }

    @GetMapping
    public ResponseEntity<List<TutorDto>> getTutors() {
        var tutors = tutorRepository.findAll();
        return ResponseEntity.ok()
                .body(
                        tutors.stream()
                                .map(tutor ->
                                        new TutorDto(
                                                tutor.getId(),
                                                tutor.getName(),
                                                tutor.getBirthday(),
                                                tutor.getGender(),
                                                tutor.getAddress(),
                                                tutor.getPhone(),
                                                tutor.getEmail())).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<Tutor> getTutorById(@PathVariable Long id) {
        var tutor = tutorRepository.findById(id);
        return tutor.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(tutor.get());
    }

    @GetMapping("nome/{name}")
    public String getTutorByNome(@PathVariable("name") String name) {
        return "Tutor com nome: " + name;
    }
}

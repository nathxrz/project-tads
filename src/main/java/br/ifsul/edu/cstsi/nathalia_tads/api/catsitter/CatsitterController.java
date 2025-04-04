package br.ifsul.edu.cstsi.nathalia_tads.api.catsitter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/catsitters")
public class CatsitterController {
    private final CatsitterRepository catsitterRepository;

    public CatsitterController(CatsitterRepository catsitterRepository){
        this.catsitterRepository = catsitterRepository;
    }

    @GetMapping
    public ResponseEntity<List<CatsitterDto>> getCatsitters() {
        var catsitters = catsitterRepository.findAll();
        return ResponseEntity.ok()
                .body(
                        catsitters.stream()
                                .map(catsitter ->
                                        new CatsitterDto(
                                                catsitter.getId(),
                                                catsitter.getName(),
                                                catsitter.getBirthday(),
                                                catsitter.getGender(),
                                                catsitter.getPhone(),
                                                catsitter.getEmail())).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<Catsitter> getCatsitterById(@PathVariable Long id) {
        var catsitter = catsitterRepository.findById(id);
        return catsitter.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(catsitter.get());
    }

    @GetMapping("nome/{name}")
    public String getCatsitterByNome(@PathVariable("name") String name) {
        return "Catsitter com nome: " + name;
    }
}

package br.ifsul.edu.cstsi.nathalia_tads.api.cat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cats")
public class CatController {
    private final CatRepository catRepository;

    public CatController(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @GetMapping
    public ResponseEntity<List<CatDto>> getCats() {
        var cats = catRepository.findAll();
        return ResponseEntity.ok()
                .body(
                        cats.stream()
                                .map(p ->
                                        new CatDto(
                                                p.getId(),
                                                p.getName(),
                                                p.getBreed())).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<Cat> getCatById(@PathVariable Long id) {
        var cat = catRepository.findById(id);
        return cat.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(cat.get());
    }

    @GetMapping("nome/{name}")
    public String getCatByNome(@PathVariable("name") String name) {
        return "Gato com nome: " + name;
    }
}

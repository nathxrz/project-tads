package br.ifsul.edu.cstsi.nathalia_tads.api.cat;

import br.ifsul.edu.cstsi.nathalia_tads.api.tutor.TutorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/cats")
public class CatController {
    private final CatRepository catRepository;
    private final TutorRepository tutorRepository;

    public CatController(CatRepository catRepository, TutorRepository tutorRepository) {
        this.catRepository = catRepository;
        this.tutorRepository = tutorRepository;
    }

    @GetMapping
    public ResponseEntity<Page<CatDTOResponse>> findAll(@PageableDefault(size = 50, sort = "name") Pageable paginacao) {
        return ResponseEntity.ok(catRepository.findAll(paginacao).map(CatDTOResponse::new));
    }

    @GetMapping("{id}")
    public ResponseEntity<CatDTOResponse> getCatById(@PathVariable Long id) {
        var cat = catRepository.findById(id);
        if (cat.isPresent()) {
            return ResponseEntity.ok(new CatDTOResponse(cat.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("nome/{name}")
    public ResponseEntity<List<CatDTOResponse>> finByNome(@PathVariable("name") String name) {
        var cats = catRepository.findCatByName(name + "%");
        return cats.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(cats.stream().map(CatDTOResponse::new).toList());
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<URI> insert(@RequestBody CatDTOPost catDTO, UriComponentsBuilder uriBuilder) {
        var tutor = tutorRepository.findById(catDTO.tutorId());

        if(tutor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var cat = catRepository.save(new Cat(
                catDTO.name(),
                catDTO.breed(),
                tutor.get()
        ));
        var location = uriBuilder.path("api/v1/cats/{id}").buildAndExpand(cat.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<CatDTOResponse> update(@PathVariable("id") Long id, @RequestBody CatDTOPut catDTO) {
        var cat = catRepository.findById(id);
        if (cat.isPresent()) {
            var c = catRepository.save(new Cat(
                    id,
                    catDTO.name(),
                    catDTO.breed()
            ));
            return ResponseEntity.ok(new CatDTOResponse(c));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CatDTOResponse> delete(@PathVariable("id") Long id) {
        var cat = catRepository.findById(id);
        if (cat.isPresent()) {
            catRepository.deleteById(id);
            return ResponseEntity.ok(new CatDTOResponse(cat.get()));
        }
        return ResponseEntity.notFound().build();
    }
}

package br.ifsul.edu.cstsi.nathalia_tads.api.cat;

import br.ifsul.edu.cstsi.nathalia_tads.api.tutor.Tutor;

import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;

@Entity
@Table(name = "cats")
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
public class Cat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String breed;

    @ManyToOne
    @JoinColumn(name = "tutor_id", referencedColumnName = "id", nullable = false)
    private Tutor tutor;

    public Cat() {
    }

    public Cat(Long id, String name, String breed, Tutor tutor) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.tutor = tutor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
}

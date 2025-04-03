package br.ifsul.edu.cstsi.nathalia_tads.api.cat;

import br.ifsul.edu.cstsi.nathalia_tads.api.tutor.Tutor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "cats")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;

    @ManyToOne
    @JoinColumn(name = "tutor_id", referencedColumnName = "id", nullable = false)
    private Tutor tutor;
}

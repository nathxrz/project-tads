package br.ifsul.edu.cstsi.nathalia_tads.api.schedule;

import br.ifsul.edu.cstsi.nathalia_tads.api.catsitter.Catsitter;
import br.ifsul.edu.cstsi.nathalia_tads.api.tutor.Tutor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTimeStart;
    private LocalDateTime dateTimeEnd;
    private String Status;

    @ManyToOne
    @JoinColumn(name = "tutor_id", referencedColumnName = "id", nullable = false)
    private Tutor tutor;

    @ManyToOne
    @JoinColumn(name = "catsitter_id", referencedColumnName = "id", nullable = false)
    private Catsitter catsitter;

}

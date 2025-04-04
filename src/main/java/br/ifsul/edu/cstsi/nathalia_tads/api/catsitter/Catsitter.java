package br.ifsul.edu.cstsi.nathalia_tads.api.catsitter;

import br.ifsul.edu.cstsi.nathalia_tads.api.schedule.Schedule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "catsitters")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Catsitter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthday;
    private String gender;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "catsitter")
    private List<Schedule> schedules;
}


package br.ifsul.edu.cstsi.nathalia_tads.api.tutor;

import br.ifsul.edu.cstsi.nathalia_tads.api.cat.Cat;
import br.ifsul.edu.cstsi.nathalia_tads.api.schedule.Schedule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tutors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Tutor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date birthday;
    private String gender;
    private String phone;
    private String email;
    private String address;

    @OneToMany(mappedBy = "tutor")
    private List<Cat> cats;

    @OneToMany(mappedBy = "tutor")
    private List<Schedule> schedules;
}
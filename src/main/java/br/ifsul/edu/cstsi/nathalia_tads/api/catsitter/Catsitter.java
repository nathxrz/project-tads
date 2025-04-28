package br.ifsul.edu.cstsi.nathalia_tads.api.catsitter;

import br.ifsul.edu.cstsi.nathalia_tads.api.schedule.Schedule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
//import lombok.Getter;
import lombok.NoArgsConstructor;
//import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "catsitters")
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter

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

    public Catsitter() {
    }

    public Catsitter(Long id, String name, LocalDate birthday, String gender, String phone, String email, List<Schedule> schedules) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.schedules = schedules;
    }

    public Catsitter(String name, LocalDate birthday, String gender, String phone, String email) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

    public Catsitter(Long id, String name, LocalDate birthday, String gender, String phone, String email) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}


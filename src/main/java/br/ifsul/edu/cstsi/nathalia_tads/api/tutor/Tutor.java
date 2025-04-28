package br.ifsul.edu.cstsi.nathalia_tads.api.tutor;

import br.ifsul.edu.cstsi.nathalia_tads.api.cat.Cat;
import br.ifsul.edu.cstsi.nathalia_tads.api.schedule.Schedule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tutors")
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter

public class Tutor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthday;
    private String gender;
    private String phone;
    private String email;
    private String address;

    @OneToMany(mappedBy = "tutor")
    private List<Cat> cats;

    @OneToMany(mappedBy = "tutor")
    private List<Schedule> schedules;

    public Tutor() {
    }

    public Tutor(Long id, String name, LocalDate birthday, String gender, String phone, String email, String address, List<Cat> cats, List<Schedule> schedules) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.cats = cats;
        this.schedules = schedules;
    }

    public Tutor(String name, LocalDate birthday, String gender, String phone, String email, String address) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Tutor(Long id, String name, LocalDate birthday, String gender, String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
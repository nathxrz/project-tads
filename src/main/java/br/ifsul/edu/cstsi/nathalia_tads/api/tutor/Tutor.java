package br.ifsul.edu.cstsi.nathalia_tads.api.tutor;

import br.ifsul.edu.cstsi.nathalia_tads.api.cat.Cat;
import br.ifsul.edu.cstsi.nathalia_tads.api.schedule.Schedule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private LocalDateTime birthday;
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

    public Tutor(Long id, String name, LocalDateTime birthday, String gender, String phone, String email, String address, List<Cat> cats, List<Schedule> schedules) {
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

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
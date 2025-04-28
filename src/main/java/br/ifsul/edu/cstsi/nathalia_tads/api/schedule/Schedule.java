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
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTimeStart;
    private LocalDateTime dateTimeEnd;
    private String status;

    @ManyToOne
    @JoinColumn(name = "tutor_id", referencedColumnName = "id", nullable = false)
    private Tutor tutor;

    @ManyToOne
    @JoinColumn(name = "catsitter_id", referencedColumnName = "id", nullable = false)
    private Catsitter catsitter;

    public Schedule() {
    }

    public Schedule(Long id, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, String status, Tutor tutor, Catsitter catsitter) {
        this.id = id;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.status = status;
        this.tutor = tutor;
        this.catsitter = catsitter;
    }

    public Schedule(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, String status, Tutor tutor, Catsitter catsitter) {
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.status = status;
        this.tutor = tutor;
        this.catsitter = catsitter;
    }

    public Schedule(Long id, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, String status) {
        this.id = id;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(LocalDateTime dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public LocalDateTime getDateTimeEnd() {
        return dateTimeEnd;
    }

    public void setDateTimeEnd(LocalDateTime dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Catsitter getCatsitter() {
        return catsitter;
    }

    public void setCatsitter(Catsitter catsitter) {
        this.catsitter = catsitter;
    }

}

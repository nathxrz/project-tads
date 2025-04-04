package br.ifsul.edu.cstsi.nathalia_tads.api.tutor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public record TutorDto (Long id, String name, LocalDateTime birthday, String gender, String phone, String email, String address)  implements Serializable {
    public TutorDto(Tutor tutor){
        this(tutor.getId(), tutor.getName(), tutor.getBirthday(), tutor.getGender(), tutor.getPhone(), tutor.getEmail(), tutor.getAddress());
    }
}
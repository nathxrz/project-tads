package br.ifsul.edu.cstsi.nathalia_tads.api.user;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity(name = "Profile")
@Table(name = "profiles")
@NoArgsConstructor
@Getter
@Setter

public class Profile implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "profiles")
    private List<User> user;

    @Override
    public String getAuthority() {
        return name;
    }
}

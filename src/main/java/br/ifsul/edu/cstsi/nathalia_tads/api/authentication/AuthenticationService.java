package br.ifsul.edu.cstsi.nathalia_tads.api.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //indica que essa classe deve ser adicionada ao Contexto do aplicativo como um Bean da camada de serviço de dados
public class AuthenticationService implements UserDetailsService {
    private final AuthenticationRepository rep;

    //indica ao Spring Boot que ele deve injetar essa dependência para a classe funcionar
    public AuthenticationService(AuthenticationRepository rep){
        this.rep = rep;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return rep.findByEmail(username);
    }
}

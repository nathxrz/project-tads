package br.ifsul.edu.cstsi.nathalia_tads.api.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthenticationController {

    private AuthenticationManager manager;

    public AuthenticationController(AuthenticationManager manager) {
        this.manager = manager;
    }

    @PostMapping("/api/v1/login")
    public ResponseEntity<String> login(@RequestBody UserAuthenticationDTO data){
        var authenticationDTO = new UsernamePasswordAuthenticationToken(data.user(), data.password());
        var authentication = manager.authenticate(authenticationDTO);
        return ResponseEntity.ok("Usuário autenticado com sucesso!");
    }
}

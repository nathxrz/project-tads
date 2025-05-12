package br.ifsul.edu.cstsi.nathalia_tads.api.authentication;

import br.ifsul.edu.cstsi.nathalia_tads.api.infra.security.TokenJwtDTO;
import br.ifsul.edu.cstsi.nathalia_tads.api.infra.security.TokenService;
import br.ifsul.edu.cstsi.nathalia_tads.api.user.User;
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

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping("/api/v1/login")
    public ResponseEntity<TokenJwtDTO> login(@RequestBody UserAuthenticationDTO data){
        var authenticationDTO = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        System.out.println("AuthenticationDTO: " + authenticationDTO);
        var authentication = manager.authenticate(authenticationDTO);
        System.out.println("Authentication: " + authentication);

        var tokenJWT = tokenService.geraToken((User) authentication.getPrincipal()); //gera o token JWT para enviar na response
        return ResponseEntity.ok(new TokenJwtDTO(tokenJWT)); //envia a response com o token JWT
    }
}

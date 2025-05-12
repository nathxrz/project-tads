package br.ifsul.edu.cstsi.nathalia_tads.api.authentication;

import br.ifsul.edu.cstsi.nathalia_tads.api.user.User;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/*
    Esta interce visa, única e exclusivamente, realizar a busca pelo usuário com UserDetails.
    Note que ela implementa Repository, ao invés de JpaRepository. Assim, a AutenticacaoRepository
    vem vazia, sem métodos CRUD, como a JpaRepository. Logo, essa interface só terá o(s) métod(s)
    implementado (s)nela.
    A responsabilidade por CRUD de usuários fica a cargo da UsuarioRepository.
 */
@RepositoryRestResource(exported = false)
public interface AuthenticationRepository extends Repository<User,Long> {
    User findByEmail(String email);
}
package br.ifsul.edu.cstsi.nathalia_tads.api.infra.security.exception;

public class TokenInvalidoException extends RuntimeException{
    public TokenInvalidoException(String mensagem) {
        super(mensagem);
    }
}
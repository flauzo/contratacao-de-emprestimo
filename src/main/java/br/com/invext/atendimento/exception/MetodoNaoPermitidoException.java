package br.com.invext.atendimento.exception;

public class MetodoNaoPermitidoException extends RuntimeException {
    public MetodoNaoPermitidoException(String message) {
        super(message);
    }
}

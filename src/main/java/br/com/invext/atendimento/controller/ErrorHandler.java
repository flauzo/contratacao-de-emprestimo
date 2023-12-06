package br.com.invext.atendimento.controller;

import br.com.invext.atendimento.exception.MetodoNaoPermitidoException;
import br.com.invext.atendimento.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MetodoNaoPermitidoException.class)
    public ResponseEntity<ApiError> handleMetodoNaoPermitidoException(MetodoNaoPermitidoException e) {
        ApiError error = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneralException(Exception e) {
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno. ...Por favor, " +
                "tente novamente mais tarde.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

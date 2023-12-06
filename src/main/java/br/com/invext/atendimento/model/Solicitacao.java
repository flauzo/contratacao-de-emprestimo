package br.com.invext.atendimento.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public record Solicitacao(String assunto) {
    // Construtor para deserialização do JSON.
    @JsonCreator
    public Solicitacao(@JsonProperty("assunto") String assunto) {
        this.assunto = assunto;
    }

    // Getter para o assunto da Solicitação.
    public String getAssunto() {
        return this.assunto;
    }
}

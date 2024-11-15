package br.com.invext.atendimento.model;

import lombok.Getter;

@Getter
public enum TipoSolicitacao {

    // Enum para representar os diferentes tipos de Solicitações.
    CARTAO("Problemas com Cartão"),
    EMPRESTIMO("Contratação de Empréstimo"),
    OUTROS("Outros assuntos");

    // Descrição do tipo de Solicitação.
    private final String descricao;

    TipoSolicitacao(String descricao) {
        this.descricao = descricao;
    }
}


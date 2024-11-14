package br.com.invext.atendimento.service;

import br.com.invext.atendimento.model.Solicitacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DistribuidorSolicitacoesTest {

    private DistribuidorSolicitacoes distribuidor;

    @BeforeEach
    public void setUp() {
        distribuidor = new DistribuidorSolicitacoes(1); // 1 Atendente por time para teste.
    }

    @Test
    public void testDistribuirSolicitacaoCartao() {
        Solicitacao solicitacao1 = new Solicitacao("Problemas com Cartão");
        Solicitacao solicitacao2 = new Solicitacao("Problemas com Cartão");
        Solicitacao solicitacao3 = new Solicitacao("Problemas com Cartão");
        Solicitacao solicitacao4 = new Solicitacao("Problemas com Cartão");

        distribuidor.distribuirSolicitacao(solicitacao1);
        distribuidor.distribuirSolicitacao(solicitacao2);
        distribuidor.distribuirSolicitacao(solicitacao3);
        distribuidor.distribuirSolicitacao(solicitacao4);

        // Como temos 1 Atendente por time, as Três primeiras solicitações devem ser Atendidas imediatamente,
        // e a Quarta solicitação deve ser enfileirada.
        Assertions.assertEquals(1, distribuidor.tamanhoFilaCartoes());
    }
}

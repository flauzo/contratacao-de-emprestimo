package br.com.invext.atendimento.service;

import br.com.invext.atendimento.model.Atendente;
import br.com.invext.atendimento.model.Solicitacao;
import br.com.invext.atendimento.model.SolicitacaoResposta;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Este é o Serviço principal que lida com a lógica de Distribuição das Solicitações. Ele verifica a disponibilidade
 * dos atendentes, encaminha as solicitações para os atendentes disponíveis ou as coloca em fila se todos os atendentes
 * estiverem ocupados. Também simula a finalização de um atendimento após um período.
 *
 */
public class DistribuidorSolicitacoes {

    public SolicitacaoResposta distribuirSolicitacao(Solicitacao solicitacao) {
        SolicitacaoResposta response = new SolicitacaoResposta();
        response.setStatus("Solicitação Recebida.");
        response.setAssunto(solicitacao.getAssunto());

        switch (solicitacao.getAssunto()) {
            case "Problemas com Cartão":
                response.setAtendimentoStatus(encaminharOuEnfileirar(solicitacao, atendentesCartoes, filaCartoes));
                response.setPosicaoNaFila(filaCartoes.size());
                break;
            case "Contratação de Empréstimo":
                response.setAtendimentoStatus(encaminharOuEnfileirar(solicitacao, atendentesEmprestimos, filaEmprestimos));
                response.setPosicaoNaFila(filaEmprestimos.size());
                break;
            default:
                response.setAtendimentoStatus(encaminharOuEnfileirar(solicitacao, atendentesOutros, filaOutros));
                response.setPosicaoNaFila(filaOutros.size());
                break;
        }
        return response;
    }

    private String encaminharOuEnfileirar(Solicitacao solicitacao, List<Atendente> atendentes, Queue<Solicitacao> fila) {
        for (Atendente atendente : atendentes) {
            if (atendente.estaDisponivel()) {
                atendente.iniciarAtendimento();
                agendarFinalizacaoAtendimento(atendente, fila);
                return "Encaminhado para um ATENDENTE.";
            }
        }
        fila.add(solicitacao);
        return "Adicionado à fila.";
    }

    private final List<Atendente> atendentesCartoes;
    private final List<Atendente> atendentesEmprestimos;
    private final List<Atendente> atendentesOutros;

    private final Queue<Solicitacao> filaCartoes;
    private final Queue<Solicitacao> filaEmprestimos;
    private final Queue<Solicitacao> filaOutros;

    public DistribuidorSolicitacoes(int qtdAtendentesPorTime) {
        atendentesCartoes = new ArrayList<>();
        atendentesEmprestimos = new ArrayList<>();
        atendentesOutros = new ArrayList<>();

        for (int i = 0; i < qtdAtendentesPorTime; i++) {
            atendentesCartoes.add(new Atendente());
            atendentesEmprestimos.add(new Atendente());
            atendentesOutros.add(new Atendente());
        }

        filaCartoes = new LinkedList<>();
        filaEmprestimos = new LinkedList<>();
        filaOutros = new LinkedList<>();
    }

    private void agendarFinalizacaoAtendimento(Atendente atendente, Queue<Solicitacao> fila) {
        scheduler.schedule(() -> {
            atendente.finalizarAtendimento();
            if (!fila.isEmpty()) {
                Solicitacao proximaSolicitacao = fila.poll();
                if (proximaSolicitacao != null) {
                    encaminharOuEnfileirar(proximaSolicitacao, List.of(atendente), fila);
                }
            }
        }, 1, TimeUnit.MINUTES); // Simula um Atendimento finalizando em 1 Minuto.
    }

    public List<Solicitacao> obterSolicitacoesPendentes() {
        List<Solicitacao> todasPendentes = new ArrayList<>();
        todasPendentes.addAll(filaCartoes);
        todasPendentes.addAll(filaEmprestimos);
        todasPendentes.addAll(filaOutros);
        return todasPendentes;
    }

    public Integer tamanhoFilaCartoes() {
        return filaCartoes.size();
    }

    public Integer tamanhoFilaEmprestimos() {
        return filaEmprestimos.size();
    }

    public Integer tamanhoFilaOutros() {
        return filaOutros.size();
    }

    // 10 Threads para simular Atendimentos simultâneos.
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

}

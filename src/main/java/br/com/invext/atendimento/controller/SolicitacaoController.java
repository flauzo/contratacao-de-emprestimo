package br.com.invext.atendimento.controller;

import br.com.invext.atendimento.exception.MetodoNaoPermitidoException;
import br.com.invext.atendimento.model.ApiError;
import br.com.invext.atendimento.model.Solicitacao;
import br.com.invext.atendimento.model.SolicitacaoResposta;
import br.com.invext.atendimento.service.DistribuidorSolicitacoes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/solicitacoes")
public class SolicitacaoController {
    // Instância do serviço de Distribuição de Solicitações.
    private final DistribuidorSolicitacoes distribuidor;

    public SolicitacaoController() {
        // Inicializa o distribuidor com 1 atendente por time.
        this.distribuidor = new DistribuidorSolicitacoes(1);
    }

    // Endpoint para adicionar uma Nova Solicitação.
    @PostMapping
    public ResponseEntity<SolicitacaoResposta> adicionarSolicitacao(@RequestBody Solicitacao solicitacao) {
        SolicitacaoResposta response = distribuidor.distribuirSolicitacao(solicitacao);
        return ResponseEntity.ok(response);
    }

    // Endpoint para obter Solicitações pendentes.
    @GetMapping("/pendentes")
    public ResponseEntity<List<Solicitacao>> solicitacoesPendentes() {
        List<Solicitacao> pendentes = distribuidor.obterSolicitacoesPendentes();
        return ResponseEntity.ok(pendentes);
    }

    // Endpoint para obter o status das filas de atendimento.
    @GetMapping("/status")
    public ResponseEntity<Map<String, Integer>> statusDasFilas() {
        HashMap<String, Integer> status = new HashMap<>();
        status.put("Fila Cartões", distribuidor.tamanhoFilaCartoes());
        status.put("Fila Empréstimos", distribuidor.tamanhoFilaEmprestimos());
        status.put("Fila Outros Assuntos", distribuidor.tamanhoFilaOutros());
        return ResponseEntity.ok(status);
    }

    @GetMapping
    public ResponseEntity<ApiError> metodoNaoPermitido() {
        throw new MetodoNaoPermitidoException("Método não permitido. ...Por favor, use POST para enviar uma Solicitação.");
    }
}


package br.com.invext.atendimento.controller;

import br.com.invext.atendimento.model.Solicitacao;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SolicitacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testAdicionarSolicitacao() throws Exception {
        Solicitacao solicitacao = new Solicitacao("Problemas com Cartão");
        mockMvc.perform(post("/api/solicitacoes")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(solicitacao)))
                .andExpect(status().isOk());
    }

    // Mais testes para outros Endpoints e cenários.
}

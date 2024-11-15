package br.com.invext.atendimento.model;

public class Atendente {
    // Contador de clientes que o Atendente está atendendo no momento.
    private int clientesAtendendo;

    public Atendente() {
        this.clientesAtendendo = 0;
    }

    // Verifica se o Atendente está disponível para atender mais clientes.
    public boolean estaDisponivel() {
        return clientesAtendendo < 3;
    }

    // Marca o início de um novo atendimento.
    public void iniciarAtendimento() {
        this.clientesAtendendo++;
    }

    // Marca a finalização de um atendimento.
    public void finalizarAtendimento() {
        this.clientesAtendendo--;
    }
}


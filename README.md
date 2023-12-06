# Solicitacoes de Contratacao de Emprestimo.
Central de Relacionamento para atender diversos tipos de solicitações dos clientes. Os principais tipos de solicitações são: Problemas com Cartão e Contratação de Empréstimo. 

## Utilização:

_Entrada de solicitações:_<br />
POST /api/solicitacoes

_Exemplos de post:_<br />
```json
{
    "assunto": "Problemas com Cartão"
}
```
```json
{
    "assunto": "Contratação de Empréstimo"
}
```
```json
{
    "assunto": "Outros Assuntos"
}
```

_Obter o status das filas:_<br />
GET /api/solicitacoes/status

_Obter as solicitações pendentes:_<br />
GET /api/solicitacoes/pendentes<br /><br />


## Software desenvolvido em relação ao seguinte cenário proposto.

1. **Tipos de Solicitações:** Reconhecer os tipos de solicitações "Problemas com Cartão" e "Contratação de Empréstimo", além de outros assuntos não especificados;<br /><br />
1. **Times de Atendimento:** Organizar os atendentes em três times: Cartões, Empréstimos e Outros Assuntos;<br /><br />
1. **Distribuição de Solicitações:** Distribuir as solicitações para o time correto com base no assunto da solicitação;<br /><br />
1. **Política de Atendimento:** Cada atendente pode atender até 3 pessoas simultaneamente. Se todos os atendentes de um time estiverem ocupados, as solicitações são enfileiradas e distribuídas assim que um atendente estiver disponível;<br /><br />
1. **API REST:** Disponibiliza uma API REST para adicionar solicitações, obter o status das filas e visualizar solicitações pendentes;<br /><br />
1. **Persistência:** O software não persiste os dados;<br /><br />
1. **Linguagem:** O software foi desenvolvido em Java.<br /><br />


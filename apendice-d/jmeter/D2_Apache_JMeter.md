<h2 align="center">CAD-MOTOTAXISTA - Documentação Técnica</h2>

### D.2 – Uso do Apache JMeter para Testes de Carga

Introdução

A avaliação de desempenho constitui uma etapa essencial no processo de validação de aplicações web, uma vez que permite mensurar sua estabilidade, eficiência e capacidade de atendimento simultâneo a múltiplas requisições. No sistema CAD-MOTOTAXISTA, essa análise busca assegurar que o ambiente tecnológico adotado seja capaz de suportar cenários reais de uso por parte da Secretaria Municipal de Trânsito e Transporte (SMTT), garantindo confiabilidade e continuidade operacional.

---
Objetivo dos Testes

Os testes de desempenho foram realizados com o objetivo de avaliar a capacidade de resposta e estabilidade do sistema CAD-MOTOTAXISTA sob carga controlada. Para isso, empregou-se a ferramenta Apache JMeter, que possibilita a simulação de múltiplos usuários acessando o sistema simultaneamente, medindo tempos médios de resposta, throughput (rendimento) e taxa de erros.

<table border="2" align="center" style="border-collapse: collapse; text-align:center;">
  <thead>
    <tr style="background-color:#2F4F4F; color:white;">
      <th colspan="2">4.X.1 – Ambiente de Teste (JMeter)</th>
    </tr>
  </thead>
  <tbody>
    <tr style="background-color:#708090; color:white;">
      <th>Componente</th>
      <th>Especificação</th>
    </tr>
    <tr>
      <td>Processador</td>
      <td>Intel® Core™ i5-1135G7 (11ª Geração) @ 2.40 GHz</td>
    </tr>
    <tr>
      <td>Memória RAM</td>
      <td>16 GB (utilizável: 15,7 GB)</td>
    </tr>
    <tr>
      <td>Sistema Operacional</td>
      <td>Windows 10, 64 bits</td>
    </tr>
    <tr>
      <td>Ferramenta de Teste</td>
      <td>Apache JMeter 5.x</td>
    </tr>
    <tr>
      <td>Número de Threads (Usuários Virtuais)</td>
      <td>20 usuários simultâneos</td>
    </tr>
    <tr>
      <td>Tipo de Teste</td>
      <td>Teste de carga com foco em tempo de resposta</td>
    </tr>
    <tr>
      <td>Cenários Avaliados</td>
      <td>Login, Cadastro de Mototaxista, Relatório CAD-MOTOTAXISTA</td>
    </tr>
  </tbody>
</table>
<p align="center"><i>A execução foi realizada de forma isolada, sem interferência de outras aplicações, garantindo a confiabilidade dos resultados.</i></p>
---
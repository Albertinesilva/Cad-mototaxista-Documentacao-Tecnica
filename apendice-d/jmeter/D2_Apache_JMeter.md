<h2 align="center">CAD-MOTOTAXISTA - Documentação Técnica</h2>

### D.2 – Uso do Apache JMeter para Testes de Carga

D.2.1 Introdução

A avaliação de desempenho constitui uma etapa essencial no processo de validação de aplicações web, uma vez que permite mensurar sua estabilidade, eficiência e capacidade de atendimento simultâneo a múltiplas requisições. No sistema CAD-MOTOTAXISTA, essa análise busca assegurar que o ambiente tecnológico adotado seja capaz de suportar cenários reais de uso por parte da Secretaria Municipal de Trânsito e Transporte (SMTT), garantindo confiabilidade e continuidade operacional.

---
D.2.2 Objetivo dos Testes de Desempenho

Os testes de desempenho foram realizados com o objetivo de avaliar a capacidade de resposta e estabilidade do sistema CAD-MOTOTAXISTA sob carga controlada. Para isso, empregou-se a ferramenta Apache JMeter, que possibilita a simulação de múltiplos usuários acessando o sistema simultaneamente, registrando métricas como:

- Tempo de resposta (média, mínimo, máximo e desvio padrão);
- Throughput (rendimento por segundo);
- Taxa de erros (% de falhas);

---
D.2.3 – Ambiente de Teste (JMeter)

Os experimentos foram conduzidos em ambiente local, utilizando a seguinte configuração de hardware e software:

<table border="2" align="center" style="border-collapse: collapse; text-align:center;">
  <thead>
    <tr style="background-color:#2F4F4F; color:white;">
      <th colspan="2">Ambiente de Teste</th>
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
      <td>Windows 11, 64 bits</td>
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
D.2.4 – Metodologia e Métricas Avaliadas

A metodologia adotada baseou-se na execução de testes de carga com usuários virtuais simultâneos, simulados pelo **Apache JMeter**, com o objetivo de identificar o comportamento do sistema CAD-MOTOTAXISTA em cenários reais de uso. Os testes foram conduzidos utilizando o seguinte fluxo de execução:

Método de Execução (Fluxo Seletivo):

1. autenticação no sistema (Login);
2. navegação e carregamento da interface de cadastro;
3. solicitação de geração de relatório do sistema.

Cada cenário foi executado com **20 threads (usuários virtuais)**, configuradas para realizar requisições consecutivas sem ramp-up prolongado, buscando reproduzir uma situação de acesso simultâneo real. Todas as respostas HTTP foram avaliadas em função de tempo de resposta e retorno de status (HTTP 2xx, 4xx, 5xx), garantindo confiabilidade dos resultados.

<table border="2" align="center" style="border-collapse: collapse; text-align:center;">
  <thead>
    <tr style="background-color:#2F4F4F; color:white;">
      <th colspan="2">Métricas Avaliadas (Apache JMeter)</th>
    </tr>
  </thead>
  <tbody>
    <tr style="background-color:#708090; color:white;">
      <th>Métrica</th>
      <th>Descrição Técnica</th>
    </tr>
    <tr>
      <td>Tempo Médio de Resposta (ms)</td>
      <td>Expressa a média do tempo que o sistema leva para processar requisições.</td>
    </tr>
    <tr>
      <td>Tempo Mínimo e Máximo (ms)</td>
      <td>Representam os extremos de latência observados durante os testes.</td>
    </tr>
    <tr>
      <td>Desvio Padrão (ms)</td>
      <td>Mede a variação entre os tempos de resposta, indicando estabilidade.</td>
    </tr>
    <tr>
      <td>Throughput (req/s)</td>
      <td>Quantidade de requisições processadas por segundo, indicando capacidade de atendimento.</td>
    </tr>
    <tr>
      <td>Taxa de Erros (%)</td>
      <td>Percentual de requisições mal-sucedidas (falhas HTTP), essencial para validar integridade.</td>
    </tr>
  </tbody>
</table>


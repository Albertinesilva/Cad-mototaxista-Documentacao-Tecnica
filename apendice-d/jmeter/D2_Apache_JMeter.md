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

<table border="2" style="border-collapse: collapse; text-align:center;">
  <thead>
    <tr style="background-color:#2F4F4F; color:white;">
      <th colspan="2">Ambiente de Teste</th>
    </tr>
  </thead>
  <tbody align="center">
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
<p><i>A execução foi realizada de forma isolada, sem interferência de outras aplicações, garantindo a confiabilidade dos resultados.</i></p>

---
D.2.4 – Metodologia e Métricas Avaliadas

A metodologia adotada baseou-se na execução de testes de carga com usuários virtuais simultâneos, simulados pelo **Apache JMeter**, com o objetivo de identificar o comportamento do sistema CAD-MOTOTAXISTA em cenários reais de uso. Os testes foram conduzidos utilizando o seguinte fluxo de execução:

Método de Execução (Fluxo Seletivo):

1. autenticação no sistema (Login);
2. navegação e carregamento da interface de cadastro;
3. solicitação de geração de relatório do sistema.

Cada cenário foi executado com **20 threads (usuários virtuais)**, configuradas para realizar requisições consecutivas sem ramp-up prolongado, buscando reproduzir uma situação de acesso simultâneo real. Todas as respostas HTTP foram avaliadas em função de tempo de resposta e retorno de status (HTTP 2xx, 4xx, 5xx), garantindo confiabilidade dos resultados.

<table border="2" style="border-collapse: collapse; text-align:center;">
  <thead>
    <tr style="background-color:#2F4F4F; color:white;">
      <th colspan="2">Métricas Avaliadas (Apache JMeter)</th>
    </tr>
  </thead>
  <tbody align="center">
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
<p><i>As métricas comprovam a estabilidade, capacidade de processamento e ausência de falhas no sistema durante a simulação de carga.</i></p>

---
D.2.5 – Resultados dos Testes de Desempenho

A tabela a seguir apresenta os resultados consolidados gerados pelo Apache JMeter, considerando a média dos tempos de resposta, variações, taxa de erros e throughput durante a simulação de 20 usuários simultâneos:

<table border="2" style="border-collapse: collapse; text-align:center;">
  <thead>
    <tr style="background-color:#2F4F4F; color:white;">
      <th colspan="8">Resultados dos Testes de Desempenho (Apache JMeter)</th>
    </tr>
    <tr style="background-color:#708090; color:white;">
      <th>Funcionalidade</th>
      <th>Amostras (n)</th>
      <th>Tempo Médio (ms)</th>
      <th>Mínimo (ms)</th>
      <th>Máximo (ms)</th>
      <th>Desvio Padrão</th>
      <th>Erro (%)</th>
      <th>Throughput</th>
    </tr>
  </thead>
  <tbody align="center">
    <tr>
      <td>Login CAD-MOTOTAXISTA</td>
      <td>20</td>
      <td>31</td>
      <td>25</td>
      <td>68</td>
      <td>9,50</td>
      <td>0,00%</td>
      <td>20,4 req/s</td>
    </tr>
    <tr>
      <td>Cadastro de Mototaxista</td>
      <td>20</td>
      <td>3521</td>
      <td>3128</td>
      <td>3914</td>
      <td>215,56</td>
      <td>0,00%</td>
      <td>4,4 req/s</td>
    </tr>
    <tr>
      <td>Relatório CAD-MOTOTAXISTA</td>
      <td>20</td>
      <td>39</td>
      <td>29</td>
      <td>52</td>
      <td>6,32</td>
      <td>0,00%</td>
      <td>20,2 req/s</td>
    </tr>
  </tbody>
</table>

<p><i>Os resultados demonstram desempenho estável e ausência de erros em todos os cenários avaliados.</i></p>

---
D.2.6 – Análise dos Resultados

A análise dos dados coletados durante os testes de desempenho permite avaliar a eficiência e a estabilidade do sistema CAD-MOTOTAXISTA diante de solicitações simultâneas. As métricas fornecidas pelo Apache JMeter indicam o comportamento da aplicação frente a diferentes operações críticas, possibilitando a identificação de gargalos e a validação de sua capacidade de atendimento em cenários reais. A seguir, são consolidados os principais resultados observados nos experimentos.

<table border="2" style="border-collapse: collapse; text-align:center;">
  <thead>
    <tr style="background-color:#2F4F4F; color:white;">
      <th colspan="3">Indicadores de Desempenho Observados</th>
    </tr>
    <tr style="background-color:#708090; color:white;">
      <th>Métrica</th>
      <th>Valor Observado</th>
      <th>Interpretação Técnica</th>
    </tr>
  </thead>
  <tbody align="center">
    <tr>
      <td>Taxa de Erros (%)</td>
      <td>0,00%</td>
      <td>Indica ausência de falhas HTTP, garantindo integridade e consistência das requisições.</td>
    </tr>
    <tr>
      <td>Tempo Médio de Resposta - Login</td>
      <td>31 ms</td>
      <td>Respostas rápidas demonstrando eficiência na autenticação de usuários.</td>
    </tr>
    <tr>
      <td>Tempo Médio de Resposta - Relatório</td>
      <td>39 ms</td>
      <td>Baixa latência no processamento e consulta de relatórios.</td>
    </tr>
    <tr>
      <td>Tempo Médio de Resposta - Cadastro</td>
      <td>3521 ms</td>
      <td>A latência mais elevada é justificada pelo carregamento de múltiplos campos e validações no front-end.</td>
    </tr>
    <tr>
      <td>Throughput Médio</td>
      <td>Estável</td>
      <td>O sistema manteve capacidade constante de atendimento, mesmo com 20 usuários simultâneos.</td>
    </tr>
  </tbody>
</table>

<p><i>Os indicadores demonstram que o sistema comporta acessos simultâneos sem degradação significativa de desempenho.</i></p>

---
D.2.7 – Evidências Visuais

As evidências de desempenho coletadas durante os testes de carga são apresentadas por meio de relatórios gráficos gerados pelo Apache JMeter. Esses registros auxiliam na interpretação prática das métricas obtidas, permitindo visualizar características como tempos de resposta, throughput, estabilidade das requisições e ausência de erros. Cada relatório representa um cenário crítico do sistema CAD-MOTOTAXISTA avaliado sob carga simultânea.

<table border="2" style="border-collapse: collapse; text-align:center;">
  <thead>
    <tr style="background-color:#2F4F4F; color:white;">
      <th colspan="2">Evidências Visuais – Relatórios do Apache JMeter</th>
    </tr>
    <tr style="background-color:#708090; color:white;">
      <th>Descrição da Evidência</th>
      <th>Link da Imagem</th>
    </tr>
  </thead>
  <tbody align="center">
    <tr>
      <td>Login CAD-MOTOTAXISTA</td>
      <td><a href="https://github.com/Albertinesilva/Cad-mototaxista-Documentacao-Tecnica/blob/main/apendice-d/jmeter/LOGIN.md" target="_blank">Aqui – Relatório de Sumário</a></td>
    </tr>
    <tr>
      <td>Cadastro CAD-MOTOTAXISTA</td>
      <td><a href="https://github.com/Albertinesilva/Cad-mototaxista-Documentacao-Tecnica/blob/main/apendice-d/jmeter/CADASTRO.md" target="_blank">Aqui – Relatório de Sumário</a></td>
    </tr>
    <tr>
      <td>Relatório CAD-MOTOTAXISTA</td>
      <td><a href="https://github.com/Albertinesilva/Cad-mototaxista-Documentacao-Tecnica/blob/main/apendice-d/jmeter/RELATORIO.md" target="_blank">Aqui – Relatório de Sumário</a></td>
    </tr>
    <tr>
      <td>Árvore de Resultados (View Results Tree) – Validação de Carregamento</td>
      <td><a href="#" target="_blank">Aqui – Árvore de Resultados</a></td>
    </tr>
  </tbody>
</table>

<p><i>As evidências reforçam a consistência e confiabilidade dos resultados apresentados, demonstrando o comportamento real do sistema durante os testes.</i></p>

---
D.2.8 Correspondência entre Casos de Avaliação e Evidências

A consolidação dos resultados obtidos durante os testes de desempenho possibilita a rastreabilidade entre os casos de avaliação definidos e as respectivas evidências geradas pelo Apache JMeter. Dessa forma, assegura-se que cada funcionalidade analisada possui comprovação visual e técnica correspondente, favorecendo a validação do comportamento do sistema frente às requisições simuladas. A Tabela 2 apresenta a relação entre os Casos de Avaliação (CA) e os registros evidenciais associados.

<table border="2" style="border-collapse: collapse; text-align:center;">
  <thead>
    <tr style="background-color:#2F4F4F; color:white;">
      <th colspan="3">Tabela – Correspondência entre Casos de Avaliação e Evidências do Apache JMeter</th>
    </tr>
    <tr style="background-color:#708090; color:white;">
      <th>Código de Avaliação</th>
      <th>Funcionalidade Testada</th>
      <th>Evidência Associada</th>
    </tr>
  </thead>
  <tbody align="center">
    <tr>
      <td>CA001</td>
      <td>Login do sistema</td>
      <td>Relatório de Sumário – Login CAD-MOTOTAXISTA</td>
    </tr>
    <tr>
      <td>CA002</td>
      <td>Acesso à tela de cadastro de mototaxista</td>
      <td>Relatório de Sumário – Cadastro CAD-MOTOTAXISTA</td>
    </tr>
    <tr>
      <td>CA003</td>
      <td>Geração de relatório CAD-MOTOTAXISTA</td>
      <td>Relatório de Sumário – Relatório CAD-MOTOTAXISTA</td>
    </tr>
  </tbody>
</table>
Fonte: Relatórios gerados pelo Apache JMeter (2025).

---
D.3 Considerações Finais dos Testes de Desempenho

A execução dos testes de desempenho utilizando o Apache JMeter permitiu avaliar de forma sistemática a estabilidade, eficiência e capacidade de resposta do sistema CAD-MOTOTAXISTA diante de acessos simultâneos. Com base nos resultados obtidos, verificou-se que o sistema demonstrou comportamento estável durante todas as requisições testadas, sem registro de falhas (0% de erros), o que evidencia a integridade das rotinas de autenticação, geração de relatórios e carregamento de interfaces.

Os tempos médios de resposta apresentados para as funcionalidades de login (31 ms) e geração de relatórios (39 ms) revelam baixo impacto computacional, indicando que tais operações não comprometem a experiência do usuário, mesmo sob múltiplas requisições concorrentes. O tempo médio superior observado no carregamento da tela de cadastro (3521 ms) é tecnicamente justificável, tendo em vista que esta operação envolve processamento adicional de scripts front-end, renderização de múltiplos componentes da interface e validações dinâmicas.

Adicionalmente, o Throughput manteve comportamento estável em todos os cenários, confirmando que o sistema é capaz de atender simultaneamente a 20 usuários virtuais sem apresentar degradação significativa de desempenho. Dessa forma, os resultados obtidos indicam que a aplicação encontra-se apta para uso operacional em ambiente de produção, atendendo às necessidades da Secretaria Municipal de Trânsito e Transporte (SMTT) com confiabilidade e desempenho adequado.

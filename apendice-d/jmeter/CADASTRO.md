<h2 align="center">CAD-MOTOTAXISTA - Documentação Técnica</h2>

Cadastro CAD-MOTOTAXISTA — Evidências de Teste

Figura 1 – Configuração da requisição HTTP para cadastro de mototaxista no sistema CAD-MOTOTAXISTA.
Esta etapa apresenta os parâmetros enviados ao servidor durante o processo de cadastro, incluindo método HTTP, rota de envio, dados persistidos e validação de entrada.
<img src="../../assets/img/cadastro/HTTP-cadastro-MOTOTAXISTA.png" alt="HTTP Cadastro" width="100%" height="100%">
**Fonte: Relatório gerado pelo Apache JMeter (2025).**

---
Figura 2 – Grupo de usuários simulando requisições concorrentes de cadastro.
O grupo de threads representa múltiplos usuários realizando cadastros simultâneos, permitindo mensurar o impacto da operação de escrita sob alta demanda.
<img src="../../assets/img/cadastro/Thread-Group.png" alt="Grupo de Usuários" width="100%" height="100%">
**Fonte: Relatório gerado pelo Apache JMeter (2025).**

---
Figura 3 – Relatório de Sumário com métricas de desempenho do cadastro de mototaxistas.
Este relatório consolida dados de tempo médio, mínimo, máximo, desvio padrão, erros e throughput, permitindo avaliar a eficiência e estabilidade do procedimento de armazenamento de dados.
<img src="../../assets/img/cadastro/Summary-Report-Cadastro-CAD-MOTOTAXISTA.png" alt="Ver Árvore de Resultados" width="100%" height="100%">
**Fonte: Relatório gerado pelo Apache JMeter (2025).**

---
Figura 4 – Árvore de Resultados validando o retorno das respostas durante o cadastro.
A estrutura hierárquica permite inspecionar individualmente a resposta do servidor, assegurando que o conteúdo retornado e os códigos HTTP sejam consistentes com a operação executada.
<img src="../../assets/img/cadastro/View-Results-Tree-Cadastro-CAD-MOTOTAXISTA.png" alt="Visualização de Resultados" width="100%" height="100%">
**Fonte: Relatório gerado pelo Apache JMeter (2025).**

Login CAD-MOTOTAXISTA — Evidências de Teste

Figura 1 – Configuração da requisição HTTP para autenticação do usuário no sistema CAD-MOTOTAXISTA.
Esta etapa demonstra os parâmetros enviados ao servidor durante o processo de login, incluindo método HTTP, URL de destino e dados necessários para validação de credenciais.
<img src="../../assets/img/login/HTTP-login-CAD-MOTOTAXISTA.png" alt="HTTP Login" width="100%" height="100%">
Fonte: Relatório gerado pelo Apache JMeter (2025).

---
Figura 2 – Configuração de carga com múltiplos usuários virtuais para simulação de acessos simultâneos.
O agrupamento de threads representa o comportamento de usuários concorrentes, permitindo mensurar o impacto do login sob demanda simultânea.
<img src="../../assets/img/login/Grupo-de-Usuários.png" alt="Grupo de Usuários" width="100%" height="100%">
Fonte: Relatório gerado pelo Apache JMeter (2025).

---
Figura 3 – Relatório de Sumário do JMeter apresentando métricas de desempenho do login.
Este relatório consolida indicadores como tempo médio de resposta, mínimo, máximo, desvio padrão e taxa de erros, confirmando a estabilidade do processo de autenticação.
<img src="../../assets/img/login/Relatorio-de-Sumario-Login-CAD-MOTOTAXISTA.png" alt="Relatório de Sumario" width="100%" height="100%">
Fonte: Relatório gerado pelo Apache JMeter (2025).

---
Figura 4 – Árvore de Resultados validando o correto carregamento das páginas durante a autenticação.
A árvore exibe respostas HTTP individuais, permitindo verificar visualmente o conteúdo retornado pelo servidor e comprovar a ausência de falhas ou redirecionamentos incorretos.
<img src="../../assets/img/login/Ver-Arvore-de-Resultados-Login-CAD-MOTOTAXISTA.png" alt="Ver Árvore de Resultados" width="100%" height="100%">
Fonte: Relatório gerado pelo Apache JMeter (2025).

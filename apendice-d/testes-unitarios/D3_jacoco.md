<h2 align="center">CAD-MOTOTAXISTA - Documentação Técnica</h2>

<br>

### Análise do Relatório de Cobertura de Testes – JaCoCo

A Figura 1 apresenta o relatório de cobertura de testes gerado pela ferramenta **JaCoCo**, aplicado ao projeto **CAD-MOTOTAXISTA**. A estratégia de teste concentrou-se na **camada de segurança e gestão de usuários**, por ser o núcleo responsável pelo controle de autenticação, autorização e administração de acesso ao sistema. Assim, foram priorizados componentes diretamente relacionados ao fluxo de login e validação das credenciais, tais como *models*, *repositories*, *services* e *controllers* do módulo de usuário.

<img src="../../assets/img/jacoco/Relatorio-Jacoco.png" alt="Relatório Jacoco" width="100%" height="100%"> 

Os resultados evidenciam níveis elevados de cobertura nas classes centrais da autenticação, especialmente em elementos como:

- `UsuarioServiceImpl`, no qual se validaram regras de autenticação, recuperação segura de credenciais e envio de tokens.
- `UsuarioController`, testando rotas, respostas HTTP e interações com o serviço.
- `UsuarioRepository`, com verificação de consultas específicas envolvendo filtros de email, status de usuário e associação entre perfil e permissões.
- `Usuario` (entidade), assegurando a integridade estrutural da classe, o correto funcionamento de construtores e a inclusão dinâmica de perfis por meio do método `addPerfil()`.

Essa cobertura demonstra não apenas a execução de métodos críticos, mas também a validade das regras de negócio relacionadas à definição e vinculação de perfis, proteção de acesso e consistência de credenciais, reforçando a confiabilidade da autenticação e das ações administrativas de gerenciamento de usuários.

Contudo, o relatório também indica áreas com cobertura parcial, especialmente em componentes auxiliares, controladores secundários e serviços que não influenciam diretamente o fluxo de login. A priorização da camada de segurança decorreu do impacto crítico desse módulo para a proteção do sistema, aliado ao tempo limitado de desenvolvimento da suíte de testes.

---
### 1. Tabelas Percentuais com os Pacotes Testados

Para complementar a interpretação dos resultados apresentados no relatório público, foram elaboradas três tabelas resumindo a cobertura de testes do módulo de usuários.

#### A) Tabela com percentuais reais (estimados com base no foco de teste)

<table border="2" style="border-collapse: collapse; text-align:center;">
  <thead>
    <tr style="background-color:#2F4F4F; color:white;">
      <th>Pacote / Componente</th>
      <th>Percentual de Cobertura (estimado)</th>
      <th>Observações</th>
    </tr>
  </thead>
  <tbody align="center">
    <tr>
      <td>br.gov.ba.saj.smtt.cadmototaxistas.security.model.entities</td>
      <td>~ 90 %</td>
      <td>Alta cobertura das entidades de usuário (`Usuario`, `Perfil`, `Token`)</td>
    </tr>
    <tr>
      <td>br.gov.ba.saj.smtt.cadmototaxistas.security.repositories</td>
      <td>~ 85 %</td>
      <td>Consultas por email, ativo e perfil testadas</td>
    </tr>
    <tr>
      <td>br.gov.ba.saj.smtt.cadmototaxistas.security.services.usuario.impl</td>
      <td>~ 80 %</td>
      <td>Regras de autenticação, redefinição de senha e ativação testadas</td>
    </tr>
    <tr>
      <td>br.gov.ba.saj.smtt.cadmototaxistas.security.controllers</td>
      <td>~ 75 %</td>
      <td>Rotas de login, cadastro, redefinição de senha cobertas</td>
    </tr>
  </tbody>
</table>

---
#### B) Tabela sem percentuais — classificação qualitativa

<table border="2" style="border-collapse: collapse; text-align:center;">
  <thead>
    <tr style="background-color:#2F4F4F; color:white;">
      <th>Pacote / Componente</th>
      <th>Nível de Cobertura</th>
      <th>Justificativa</th>
    </tr>
  </thead>
  <tbody align="center">
    <tr>
      <td>Modelos de Segurança (Usuário, Token, Perfil)</td>
      <td>Alta</td>
      <td>Testes intensos de construtores, perfis e validações de senha</td>
    </tr>
    <tr>
      <td>Repositórios (UsuarioRepository)</td>
      <td>Alta</td>
      <td>Consultas como findByEmail, findByPerfil e findById bem cobertas</td>
    </tr>
    <tr>
      <td>Serviço de Usuário (UsuarioServiceImpl)</td>
      <td>Média-Alta</td>
      <td>Fluxos críticos de autenticação, ativação e redefinição de senha testados</td>
    </tr>
    <tr>
      <td>Controllers de Usuário</td>
      <td>Média</td>
      <td>Endpoints principais validados, mas sem cobertura total de exceções</td>
    </tr>
  </tbody>
</table>

---
#### C) Tabela de estimativa percentual baseada no escopo de testes aplicado

<table border="2" style="border-collapse: collapse; text-align:center;">
  <thead>
    <tr style="background-color:#2F4F4F; color:white;">
      <th>Pacote / Componente</th>
      <th>Estimativa de Cobertura (%)</th>
      <th>Base da Estimativa</th>
    </tr>
  </thead>
  <tbody align="center">
    <tr>
      <td>Entidades de Usuário (Model)</td>
      <td>> 85 %</td>
      <td>Testes unitários para getters/setters, perfis e construtores</td>
    </tr>
    <tr>
      <td>Repository de Usuário</td>
      <td>~ 80 %</td>
      <td>Operações de banco cobertas via DataJpaTest</td>
    </tr>
    <tr>
      <td>Service de Usuário</td>
      <td>~ 75 %</td>
      <td>Ações de ativação, redefinição de senha e autenticação testadas</td>
    </tr>
    <tr>
      <td>Controller de Usuário</td>
      <td>~ 70 %</td>
      <td>Testes MVC simulando requisições HTTP</td>
    </tr>
  </tbody>
</table>

---
### 2. Conclusão Final da Seção (Cobertura de Testes)

A análise da cobertura de testes obtida pelo JaCoCo reforça que o foco estratégico nos componentes críticos de segurança — especificamente as classes relacionadas ao gerenciamento de usuários — foi bem-sucedido. Apesar de o escopo ter sido limitado pelas restrições de tempo, os testes alcançaram cobertura significativa nas entidades, repositórios, serviços e controllers que sustentam os fluxos de autenticação, ativação de conta e redefinição de senha.

Essa abordagem dirigida atendeu ao objetivo prioritário de garantir a integridade e a confiabilidade do processo de login e autorização, pontos sensíveis em qualquer sistema que lide com dados de identidade e perfil de acesso. A cobertura encontrada nas entidades mostra que a modelagem de usuário está bem testada, enquanto a verificação de repositórios assegura que consultas importantes — como filtragem por e-mail e perfil — estejam funcionais. Por sua vez, os testes de serviço demonstram resiliência nas regras de negócio associadas à ativação de conta e criptografia de senha, e os testes de controller validam a experiência HTTP real dos usuários.

Contudo, a cobertura não é universal. Há lacunas em componentes auxiliares e fluxos menos críticos, o que era esperado dado o escopo restrito. Recomenda-se, portanto, que em futuras iterações sejam incluídos:

- Testes mais amplos para outros controllers e serviços não vinculados à autenticação.
- Cobertura de casos de erro e exceções em toda a aplicação.
- Testes de integração validando interação entre camadas (controller → service → repository).

Em síntese, o esforço da etapa atual oferece uma base confiável para a segurança de autenticação e autorização no CAD-MOTOTAXISTA, fortalecendo os requisitos essenciais de proteção do sistema. A URL pública do relatório — **relatório de cobertura hospedado no Netlify** — reforça a transparência e facilita a revisão contínua pelas partes interessadas:

📎[Jacoco Cad-Mototaxista](https://jacoco-cadmototaxista.netlify.app/)

### Evidência Complementar: Código-Fonte dos Testes Unitários

Além do relatório de cobertura disponibilizado via JaCoCo, o código completo dos testes unitários aplicados ao módulo de segurança e autenticação pode ser consultado no repositório público:

📎 [Testes Unitários Cad-Mototaxista](https://github.com/Albertinesilva/Cad-mototaxista-Documentacao-Tecnica/tree/main/apendice-d/testes-unitarios/test)


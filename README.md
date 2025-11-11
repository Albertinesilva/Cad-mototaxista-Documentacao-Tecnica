<h2 align="center">Cad-Mototaxista - Documentação Técnica</h2>

### 🏍️ Sistema CADMOTOTAXISTA
Sistema de Gestão de Mototaxistas e Motofretistas da SMTT de Santo Antônio de Jesus

O CADMOTOTAXISTA é um sistema desenvolvido para a Secretaria Municipal de Trânsito e Transporte (SMTT) do município de Santo Antônio de Jesus (BA), com o objetivo de gerenciar o cadastro, regulamentação e fiscalização de profissionais de transporte individual — mototaxistas e motofretistas.

A aplicação foi construída com Spring Boot, Thymeleaf e PostgreSQL, seguindo a arquitetura MVC e incorporando recursos como:

- Emissão automática de alvarás em formato PDF via JasperReports;
- Controle de usuários e perfis de acesso com Spring Security;
- Migrations versionadas com Flyway;
- Integração externa com o serviço público ViaCEP para consulta automática de endereços;
- Validação e auditoria de dados centralizadas na camada de serviços.

Além disso, o repositório contém a documentação completa do sistema, incluindo JavaDocs, scripts de banco de dados, estrutura DDL, e instruções de execução e deploy.

---
<!-- Quadro: Estrutura dos Módulos -->
<table>
  <thead>
    <tr>
      <th align="center">Módulo</th>
      <th align="center">Descrição</th>
      <th align="center">Link</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Controller do Módulo de Alvará</strong></td>
      <td>Controla as requisições de geração e emissão dos relatórios e alvarás do sistema.</td>
      <td><a href="https://github.com/Albertinesilva/Cadmototaxista-Documentacao-Tecnica/blob/main/controller/C1_Controller_Alvara.md">JasperController</a></td>
    </tr>
    <tr>
      <td><strong>Service do Módulo de Alvará</strong></td>
      <td>Camada responsável pela geração e processamento dos relatórios JasperReports.</td>
      <td><a href="https://github.com/Albertinesilva/Cadmototaxista-Documentacao-Tecnica/blob/main/service/C2_Service_Alvara.md">JasperService</a></td>
    </tr>
    <tr>
      <td><strong>Segurança e Autenticação</strong></td>
      <td>Configuração do Spring Security (perfis, login, logout e controle de sessão).</td>
      <td><a href="https://github.com/Albertinesilva/Cadmototaxista-Documentacao-Tecnica/blob/main/security/C3_Seguranca_Autenticacao.md">Spring Security</a></td>
    </tr>
    <tr>
      <td><strong>Repositórios (Repositories)</strong></td>
      <td>Persistência de dados via Spring Data JPA.</td>
      <td><a href="https://github.com/Albertinesilva/Cadmototaxista-Documentacao-Tecnica/blob/main/repository/C4_Repositories.md">Spring Data JPA</a></td>
    </tr>
    <tr>
      <td><strong>Regras de Negócio Sensíveis</strong></td>
      <td>Validações críticas e verificação de vencimento de alvará.</td>
      <td><a href="https://github.com/Albertinesilva/Cadmototaxista-Documentacao-Tecnica/blob/main/service/metodos/C5_Regras_Negocio_Sensiveis.md">Regras Sensíveis</a></td>
    </tr>
    <tr>
      <td><strong>Integração com ViaCEP</strong></td>
      <td>Busca automática de endereços via API pública.</td>
      <td><a href="https://github.com/Albertinesilva/Cadmototaxista-Documentacao-Tecnica/blob/main/api/C5_1_Integracao_ViaCEP.md">Integração ViaCEP</a></td>
    </tr>
    <tr>
      <td><strong>Controle de Versões com Flyway</strong></td>
      <td>Versionamento e migração de banco de dados.</td>
      <td><a href="https://github.com/Albertinesilva/Cadmototaxista-Documentacao-Tecnica/blob/main/db/apontamento.md">Migrations</a></td>
    </tr>
    <tr>
      <td><strong>Estrutura DDL do Banco</strong></td>
      <td>Definição das tabelas, chaves e relacionamentos do banco de dados.</td>
      <td><a href="https://github.com/Albertinesilva/Cadmototaxista-Documentacao-Tecnica/blob/main/db/DDL/C7_Estrutura_DDL_PostgreSQL.md"> Estrutura DDL</a></td>
    </tr>
    <tr>
      <td><strong>Diagrama do Banco de Dados</strong></td>
      <td>Representação visual das relações entre entidades e tabelas do sistema.</td>
      <td><a href="https://github.com/Albertinesilva/Cadmototaxista-Documentacao-Tecnica/blob/main/db/diagrama/modelo_relacional.md">Diagrama ER</a></td>
    </tr>
    <tr>
      <td><strong>Documentação JavaDocs</strong></td>
      <td>Documentação técnica gerada automaticamente (JavaDocs).</td>
      <td><a href="https://documentacao-cadmototaxista-javadocs.netlify.app/">JavaDocs (Netlify)</a></td>
    </tr>
    <tr>
      <td><strong>Execução do Sistema</strong></td>
      <td>Instruções de configuração, variáveis de ambiente e deploy no Heroku.</td>
      <td><a href="https://github.com/Albertinesilva/Cadmototaxista-Documentacao-Tecnica/blob/main/config/C9_Execucao_Sistema.md">Execução do Sistema</a></td>
    </tr>
  </tbody>
</table>

---
### 📘 Licença de Uso e Distribuição – Albert Silva de Jesus (v1.0)

Copyright © 2025 **Albert Silva de Jesus**.  
Todos os direitos reservados.

Este software e sua documentação foram desenvolvidos por **Albert Silva de Jesus**.  
A cópia, modificação, distribuição ou redistribuição total ou parcial do código-fonte e/ou da documentação só é permitida mediante **autorização expressa do autor**.

É permitido o uso para **fins acadêmicos, educacionais ou de referência técnica**, desde que seja mantida a devida **citação do autor** e **não haja fins comerciais**.

O autor **não se responsabiliza por danos diretos ou indiretos** resultantes do uso deste software, incluindo falhas, perdas de dados ou prejuízos decorrentes da utilização indevida ou não autorizada do código.

---

| 🌎 LinkedIn                                                              | 👨‍💻 **Autor**                                                                 |
| ------------------------------------------------------------------------ | ---------------------------------------------------------------------------- |
| [LinkedIn](https://www.linkedin.com/in/albert-backend-java-spring-boot/) | [Albert Silva](https://www.linkedin.com/in/albert-backend-java-spring-boot/) |


<h2 align="center">CAD-MOTOTAXISTA - Documentação Técnica</h2>

<br>

### Modelo Relacional do Banco de Dados

O diagrama a seguir representa o **modelo físico relacional** adotado pelo sistema **CAD-MOTOTAXISTA**, descrevendo a estrutura das tabelas do banco de dados, seus atributos e os relacionamentos existentes no SGBD utilizado pela aplicação. Esse modelo reflete a implementação real do banco, apresentando **tipos de dados, chaves primárias, chaves estrangeiras, colunas específicas e restrições**, características inerentes aos modelos físicos em ambiente de produção.

A organização das entidades representa a estrutura operacional do sistema, contemplando o **cadastro completo de mototaxistas**, incluindo informações pessoais, documentos, motocicletas, pontos de moto, contatos, auditoria de ações, além dos mecanismos de autenticação e autorização, como **perfis, tokens e relacionamentos entre usuários e permissões**. Dessa forma, o modelo físico garante:

- **Integridade referencial**
- **Consistência de domínio**
- **Rastreabilidade de operações**
- **Confiabilidade na persistência de informações críticas**

Esses aspectos são fundamentais para a **fiscalização e gestão pública dos condutores**, assegurando segurança e confiabilidade do processo de controle dos serviços de transporte individual por motocicleta.

<br>

  <img src="../../assets/img/Modelo_Relacional.png" alt="Modelo Relacional" width="100%" height="100%">

**Fonte:** Modelo físico relacional do sistema CAD-MOTOTAXISTA (2025).

### 📌 Estrutura de Agrupamento das Tabelas

As entidades do modelo físico do sistema CAD-MOTOTAXISTA podem ser organizadas em grupos funcionais, conforme seus papéis no processo de gestão de condutores, autenticação e auditoria de ações. Essa segmentação favorece a compreensão do modelo sob uma perspectiva arquitetural e operacional:

- **🔐 Segurança e Autenticação**
  - Tabelas relacionadas ao controle de usuários, perfis, permissões e tokens de acesso.
- **🛵 Cadastro Operacional**
  - Tabelas destinadas ao registro dos mototaxistas, suas motocicletas, pontos de moto, contatos e parâmetros funcionais.
- **📬 Comunicação Institucional**
  - Tabelas responsáveis por armazenar mensagens e registros de comunicação entre o órgão gestor e os usuários.
- **📋 Auditoria e Rastreabilidade**
  - Tabelas destinadas ao monitoramento e à rastreabilidade de ações executadas pelos usuários no sistema.
- **👥 Gestão Administrativa**
  - Tabelas responsáveis pelo armazenamento de dados de funcionários, responsáveis e gestores do sistema.

---

### 📊 Tabela de Entidades e Funções no Modelo Relacional

| **Tabela**                 | **Propósito Operacional**                                   | **Chaves**                                                                        | **Tipo / Categoria** |
| -------------------------- | ----------------------------------------------------------- | --------------------------------------------------------------------------------- | -------------------- |
| `condutor`                 | Registro completo dos mototaxistas cadastrados no município | PK: `id`, FK: `cnh_fk`, `endereco_fk`, `contato_fk`, `motocicleta_fk`, `ponto_fk` | Cadastro Operacional |
| `cnh`                      | Armazena dados da Carteira Nacional de Habilitação          | PK: `id`, FK: —                                                                   | Cadastro Operacional |
| `motocicleta`              | Guarda informações da motocicleta utilizada pelo condutor   | PK: `id`, FK: `moto_modelo_fk`                                                    | Cadastro Operacional |
| `moto_modelo`              | Modelos de motocicletas permitidos                          | PK: `id`, FK: `marca_fk`                                                          | Cadastro Operacional |
| `moto_marca`               | Marcas de motocicletas                                      | PK: `id`                                                                          | Cadastro Operacional |
| `ponto_moto`               | Locais de atuação dos mototaxistas                          | PK: `id`, FK: `contato_fk`, `endereco_fk`                                         | Cadastro Operacional |
| `cliente`                  | Registra dados de clientes que utilizam os serviços         | PK: `id`, FK: `contato_fk`, `endereco_fk`, `id_usuario`                           | Cadastro Operacional |
| `registro_cliente_contato` | Histórico de contatos cliente → condutor                    | PK: `id`                                                                          | Cadastro Operacional |
| `contato`                  | Armazena números e meios de contato                         | PK: `id`                                                                          | Cadastro Operacional |
| `endereco`                 | Registra localização física de pessoas e entidades          | PK: `id`                                                                          | Cadastro Operacional |
| `mail`                     | Histórico de comunicações por e-mail                        | PK: `id`                                                                          | Comunicação          |
| `auditoria_registros`      | Registro de atividades executadas no sistema                | PK: `id`, FK: `usuario_id`                                                        | Auditoria            |
| `usuarios`                 | Conta de acesso do sistema                                  | PK: `id`                                                                          | Segurança            |
| `perfis`                   | Perfis de usuário (papéis)                                  | PK: `id`                                                                          | Segurança            |
| `usuarios_tem_perfis`      | Relaciona usuários aos seus perfis                          | PK: composta (`perfil_id`, `usuario_id`)                                          | Segurança            |
| `tokens`                   | Tokens JWT para sessão e expiração                          | PK: `id`, FK: `usuario_id`                                                        | Segurança            |
| `funcionario`              | Dados dos servidores responsáveis pelo sistema              | PK: `id`, FK: `id_usuario`                                                        | Administração        |

---

### 📑 Integridade e Regras de Negócio no Banco

O modelo físico do CAD-MOTOTAXISTA implementa mecanismos de **integridade referencial, restrições de domínio e regras de negócio em nível de banco**, fundamentais para garantir confiabilidade na gestão pública digital. Entre as políticas adotadas, destacam-se:

#### 🔒 **Integridade Referencial**

- Uso sistemático de **chaves estrangeiras** para relacionar condutores, veículos, endereços, contatos, pontos de moto, usuários e auditoria.
- Bloqueio de exclusões que comprometam a rastreabilidade, utilizando políticas como **ON DELETE RESTRICT** e **ON DELETE SET NULL**.

#### 🛑 **Restrição de Domínio**

- Definição explícita de tipos de dados coerentes com normas legais (ex.: tamanho de CPF, CNH e placa veicular).
- Controle de **valores booleanos** (ex.: `ativo`, `desativado`) para assegurar consistência operacional.
- Limitação descritiva por `varchar` com semântica específica para cada campo.

#### 🔎 **Rastreabilidade e Auditoria**

- Registro automático de operações sensíveis, incluindo ações administrativas e de autenticação.
- Associação direta entre logs e usuários responsáveis, permitindo transparência e responsabilização.

#### 👮 **Regras de Negócio em Nível de Banco**

- Cada motocicleta deve estar vinculada a apenas **um condutor ativo por vez**.
- Condutores inativos não podem ser vinculados a novos pontos de moto ou receber solicitações.
- As permissões de usuários são reguladas por perfis, garantindo **segregação de funções e controle de acesso**.

---

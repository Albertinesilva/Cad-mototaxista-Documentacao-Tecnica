<h2 align="center">CadMototaxista - Documentação Técnica</h2>

### Controle de Versões de Banco de Dados com Flyway

O sistema **CADMOTOTAXISTA** utiliza a ferramenta **Flyway** para realizar o **versionamento e controle de evolução do banco de dados**, garantindo rastreabilidade e integridade das alterações estruturais ao longo do ciclo de desenvolvimento.

Principais características:
- Executa scripts SQL em ordem sequencial, seguindo o padrão de nomenclatura `VXX__descricao.sql`;  
- Cada versão (`V01`, `V02`, … `V27`) representa uma alteração, desde a **criação inicial das tabelas (CREATE TABLE)** até **ajustes e evoluções estruturais (ALTER TABLE)**;  
- Facilita manutenção, auditoria e reprodutibilidade do banco de dados em diferentes ambientes de desenvolvimento e produção;
#### Disponivel em: [Migratios](https://github.com/Albertinesilva/workshop-javafx-jdbc)
---
### Estrutura DDL do Banco de Dados

O sistema **CADMOTOTAXISTA** utiliza um **banco de dados relacional** projetado para garantir **integridade referencial, consistência e rastreabilidade** das informações da aplicação.

Principais características:
- Contempla entidades centrais: **condutor, cliente, funcionário, motocicleta e usuário**;  
- Inclui tabelas auxiliares para gerenciamento de **endereços, contatos, CNHs e auditoria**;  
- A **Definição de Dados (DDL)** estabelece as instruções SQL para criação de **tabelas, índices e restrições de integridade**, garantindo coerência entre chaves primárias e estrangeiras;  
- Centraliza todas as instruções que sustentam o **modelo lógico do banco de dados**, facilitando manutenção e auditoria.
#### Disponivel em: [DDL](https://github.com/Albertinesilva/Cadmototaxista-Documentacao-Tecnica/blob/main/db/DDL/C7_Estrutura_DDL_PostgreSQL.md)
#### Disponivel em: [Diagrama](https://github.com/Albertinesilva/Cadmototaxista-Documentacao-Tecnica/blob/main/db/diagrama/modelo_relacional.md)



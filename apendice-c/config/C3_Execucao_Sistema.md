<h2 align="center">CAD-MOTOTAXISTA - Documentação Técnica</h2>

### 1 Execução do Sistema

A execução do sistema CADMOTOTAXISTA ocorre por meio da aplicação Spring Boot, que integra todos os módulos de backend, frontend (Thymeleaf) e acesso ao banco de dados PostgreSQL.
O processo de inicialização foi projetado para garantir o carregamento ordenado dos componentes, a verificação de dependências e a disponibilidade dos serviços essenciais da aplicação.

---
#### 1.1 Inicialização da Aplicação

A execução é iniciada pelo comando:
```java
mvn spring-boot:run
```
ou, em ambientes de produção:
```java
java -jar cadmototaxista.jar
```

Durante o processo de inicialização, o Spring Boot realiza:

- Leitura das configurações definidas no arquivo application.properties;
- Conexão automática ao banco de dados PostgreSQL;
- Execução do controle de versões de banco de dados via Flyway;
-Registro dos beans e serviços da aplicação;
- Inicialização do servidor embutido Apache Tomcat, responsável por disponibilizar a interface web.
---

#### 2. Acesso à Interface Web

Após a inicialização, o sistema é acessível por meio do navegador:
```properties
http://localhost:8080/
```
---
#### 2.1 Credenciais de Acesso para Testes (Ambiente de Demonstração)

- Para fins de teste e navegação pela banca examinadora, um usuário institucional foi criado exclusivamente para esse ambiente:

Acesso ao Sistema:
```http
https://cad-mototaxistas.herokuapp.com
```

Credenciais para Testes
```javaScript
E-mail: cadmototaxista@saj.gov.br
```

```javaScript
Senha: Teste@2025
```

#### 🔗 Disponivel em: [CAD-MOTOTAXISTA](https://cad-mototaxistas.herokuapp.com)
> ⚠️ Observação: Essas credenciais são destinadas apenas para avaliação acadêmica e não devem ser utilizadas em ambientes de produção.
---

#### 2.2 Funcionalidades Disponíveis na Interface
A interface gráfica, desenvolvida com Thymeleaf e Bootstrap, fornece acesso aos módulos administrativos da Secretaria Municipal de Trânsito e Transporte (SMTT), permitindo:

- Gerenciamento de Condutores, Clientes e Funcionários;
- Controle de Alvarás, CNHs e Motocicletas;
- Emissão de relatórios no JasperReports;
- Envio de mensagens automáticas via WhatsApp Web;
- Auditoria de operações e controle de usuários do sistema.

---

#### 3. Estrutura de Logs e Monitoramento

Durante a execução, o sistema gera registros detalhados de eventos no console e no diretório logs/, incluindo:

- Informações de inicialização e encerramento;
- Mensagens de sucesso e erro em operações críticas;
- Registros de acesso e autenticação;
- Eventos de persistência de dados e validações.
- Esses logs são fundamentais para o monitoramento do ambiente de produção, permitindo identificar rapidamente falhas ou inconsistências.

---

#### 4. Configuração do application.properties

O arquivo `application.properties` concentra as principais configurações do sistema, incluindo idioma, banco de dados, codificação, controle de sessões, envio de e-mails e integrações externas.
Os parâmetros sensíveis (usuário, senha e chaves de segurança) são definidos como variáveis de ambiente para garantir segurança e portabilidade entre os ambientes de desenvolvimento e produção.

```properties
# LOCALE
spring.web.locale-resolver=fixed
spring.web.locale=pt_BR

# POSTGRESQL
spring.datasource.url=jdbc:postgresql://localhost:5432/cad_mototaxista?createDatabaseIfNotExist=true&serverTimezone=America/Sao_Paulo
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=${POSTGRES_DATASOURCE_USER}
spring.datasource.password=${POSTGRES_DATASOURCE_PASSWORD}
logging.level.org.springframework=DEBUG
logging.level.org.flywaydb=DEBUG

# JPA
# spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
# spring.jpa.open-in-view=true
spring.jpa.properties.hibernate.format_sql=true

# Codificação de scripts SQL e requisições HTTP
spring.sql.init.encoding=UTF-8
server.servlet.encoding.charset=UTF-8
server.servlet.encoding.enabled=true
server.servlet.encoding.force=true

# Flyway
spring.flyway.enabled=true

# Inicialização de scripts SQL
spring.sql.init.mode=never
spring.sql.init.platform=postgres
spring.sql.init.data-locations=classpath:db/data/S*__*.sql

# THYMELEAF
spring.thymeleaf.cache=false

# URLs
backend.url=http://localhost:8080
frontend.Url=email/confirmacao

# JAVAMAIL
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_PASSWORD}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.ssl.trust=smtp.gmail.com
spring.mail.test-connection=true

# SESSÕES E SEGURANÇA
server.servlet.session.timeout=${SESSION_TIMEOUT}
app.security.remember-me-key=${REMEMBER_ME_KEY}

# Geração opcional de script de banco
spring.jpa.properties.javax.persistence.schema-generation.create-source=metadata
spring.jpa.properties.javax.persistence.schema-generation.scripts.action=create
spring.jpa.properties.javax.persistence.schema-generation.scripts.create-target=create.sql
spring.jpa.properties.hibernate.hbm2ddl.delimiter=;
spring.jpa.properties.hibernate.format_sql=true
```
> 💡 **Observação**: O arquivo application.properties deve ser ajustado conforme o ambiente, principalmente nas propriedades de conexão com o banco (spring.datasource.url, username e password).
---

#### 5 Encerramento Controlado da Aplicação

O encerramento da execução do sistema pode ser realizado de forma simples e segura.
Caso a aplicação esteja sendo executada via terminal, basta pressionar a combinação de teclas `Ctrl + C`, o que interrompe o processo do servidor embutido do Spring Boot (geralmente o `Tomcat`).

Se a aplicação estiver sendo executada a partir de uma `IDE` (como `IntelliJ IDEA`, `Eclipse` ou `VS Code`), o encerramento pode ser feito diretamente pelo painel de controle de execução, utilizando o botão **“Stop”** disponível na interface da IDE ou em extensões de gerenciamento de execução.

Essa ação interrompe o servidor de aplicação de forma controlada, liberando as portas utilizadas e encerrando as conexões:

- Conexões de banco de dados;
- Threads em execução;
- Cache e contexto de sessão.

---
#### 6. Deploy da Aplicação no Heroku

O deploy do sistema CADMOTOTAXISTA é realizado na plataforma Heroku, que oferece um ambiente de hospedagem em nuvem totalmente integrado ao Git.
O processo de implantação foi configurado de forma automatizada, permitindo que, a cada push realizado no repositório remoto vinculado ao Heroku, a aplicação seja automaticamente compilada, empacotada e publicada no ambiente de produção.

Esse mecanismo de integração contínua simplifica a atualização do sistema, dispensando etapas manuais de compilação e upload de artefatos.
O fluxo padrão de implantação consiste nas seguintes etapas:

6.1 Etapas do Deploy
#### 1.Realizar o commit das alterações locais:
```bash
git add .
git commit -m "Atualização de funcionalidades ou correções"
```
#### 2.Enviar as alterações para o repositório remoto vinculado ao Heroku
```bash
git push ou git push heroku main
```

#### 3.Após o envio, o Heroku executa automaticamente o build do projeto, configurando o ambiente de execução (Java + Spring Boot) e iniciando o servidor de aplicação.

Uma vez concluído o processo, a aplicação fica disponível publicamente no endereço fornecido pela plataforma, geralmente no formato:

```http
https://cad-mototaxistas.herokuapp.com
```

Esse modelo de deploy contínuo garante agilidade, rastreabilidade e consistência nas publicações, sendo especialmente adequado para ambientes de desenvolvimento e demonstração acadêmica.

| 🌎 LinkedIn                                                              | 👨‍💻 **Autor**                                                                 |
| ------------------------------------------------------------------------ | ---------------------------------------------------------------------------- |
| [LinkedIn](https://www.linkedin.com/in/albert-backend-java-spring-boot/) | [Albert Silva](https://www.linkedin.com/in/albert-backend-java-spring-boot/) |

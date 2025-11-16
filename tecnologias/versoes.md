<h2 align="center">CAD-MOTOTAXISTA - Documentação Técnica</h2>


<table>
  <thead>
    <tr style="background-color:#2F4F4F; color:white;">
      <th colspan="3">Tecnologias e Bibliotecas Utilizadas no Projeto CAD-MOTOTAXISTA</th>
    </tr>
    <tr style="background-color:#708090; color:white;">
      <th>Tecnologia / Biblioteca</th>
      <th>Versão</th>
      <th>Finalidade</th>
    </tr>
  </thead>
  <tbody>
    <!-- Backend -->
     <tr style="background-color:#2F4F4F; color:white;">
      <th colspan="3">Backend / Servidor</td></th>
    </tr>
    <tr>
      <td>Java</td>
      <td>17 (LTS)</td>
      <td>Linguagem principal para lógica de negócio e backend.</td>
    </tr>
    <tr>
      <td>Spring Boot</td>
      <td>3.4.4</td>
      <td>Framework principal para desenvolvimento rápido de aplicações Java.</td>
    </tr>
    <tr>
      <td>Spring Data JPA</td>
      <td>3.4.4</td>
      <td>Persistência de dados e mapeamento objeto-relacional.</td>
    </tr>
    <tr>
      <td>Spring MVC (Web)</td>
      <td>3.4.4</td>
      <td>Desenvolvimento de APIs REST e renderização de páginas web.</td>
    </tr>
    <tr>
      <td>Spring Security</td>
      <td>3.4.4</td>
      <td>Autenticação, autorização e segurança de endpoints.</td>
    </tr>
    <tr>
      <td>Spring Security Test</td>
      <td>3.4.4</td>
      <td>Suporte a testes unitários de componentes com segurança.</td>
    </tr>
    <tr>
      <td>Spring Boot DevTools</td>
      <td>3.4.4</td>
      <td>Hot reload para desenvolvimento ágil.</td>
    </tr>
    <tr>
      <td>Spring Boot Starter Mail</td>
      <td>3.4.4</td>
      <td>Envio de e-mails para notificações do sistema.</td>
    </tr>
    <tr>
      <td>Spring Boot Starter Validation</td>
      <td>3.4.4</td>
      <td>Validação de dados de entrada via javax.validation.</td>
    </tr>
    <tr>
      <td>Thymeleaf</td>
      <td>3.1.0</td>
      <td>Motor de templates HTML dinâmicos.</td>
    </tr>
    <tr>
      <td>Thymeleaf Layout Dialect</td>
      <td>3.1.0</td>
      <td>Layouts reutilizáveis e padronização visual de templates.</td>
    </tr>
    <tr>
      <td>Thymeleaf Extras Spring Security</td>
      <td>3.1.0</td>
      <td>Exibição condicional de conteúdo conforme permissões de usuário.</td>
    </tr>
    <tr>
      <td>PostgreSQL Driver</td>
      <td>15.x</td>
      <td>Conector JDBC para comunicação com PostgreSQL.</td>
    </tr>
    <tr>
      <td>H2 Database</td>
      <td>2.2.220</td>
      <td>Banco em memória para testes unitários e integração.</td>
    </tr>
    <tr>
      <td>Flyway (PostgreSQL)</td>
      <td>11.9.1</td>
      <td>Migração e versionamento do esquema de banco de dados.</td>
    </tr>
    <tr>
      <td>Lombok</td>
      <td>1.18.x</td>
      <td>Reduz código boilerplate com getters, setters e construtores.</td>
    </tr>
    <tr>
      <td>WebJars Locator Core</td>
      <td>2.x</td>
      <td>Localização automática de bibliotecas WebJars.</td>
    </tr>
    <tr>
      <td>JasperReports</td>
      <td>6.20.5</td>
      <td>Geração de relatórios em PDF, HTML, XLS e outros formatos.</td>
    </tr>
    <tr>
      <td>JasperReports Fonts</td>
      <td>6.20.5</td>
      <td>Conjunto de fontes compatíveis com JasperReports.</td>
    </tr>
    <tr>
      <td>Barcode4J</td>
      <td>2.1</td>
      <td>Geração de códigos de barras em relatórios.</td>
    </tr>
    <tr>
      <td>ZXing Core</td>
      <td>3.4.0</td>
      <td>Geração e leitura de QR codes.</td>
    </tr>
    <tr>
      <td>Apache Commons Text</td>
      <td>1.9</td>
      <td>Manipulação avançada de strings e textos.</td>
    </tr>
    <tr>
      <td>Batik Bridge</td>
      <td>1.16</td>
      <td>Renderização de gráficos SVG para relatórios.</td>
    </tr>
    <tr>
      <td>Commons Logging</td>
      <td>1.2</td>
      <td>Framework de logging usado por bibliotecas do projeto.</td>
    </tr>
    <tr>
      <td>Commons BeanUtils</td>
      <td>1.9.4</td>
      <td>Manipulação de beans Java em JasperReports e outras bibliotecas.</td>
    </tr>
    <tr>
      <td>JUnit 5 (Jupiter)</td>
      <td>5.x</td>
      <td>Framework de testes unitários e integração.</td>
    </tr>
    <tr>
      <td>Mockito Core</td>
      <td>5.x</td>
      <td>Criação de mocks para testes unitários.</td>
    </tr>
    <tr>
      <td>JaCoCo Maven Plugin</td>
      <td>0.8.7</td>
      <td>Geração de relatórios de cobertura de testes.</td>
    </tr>
    <tr>
      <td>Maven Javadoc Plugin</td>
      <td>3.6.3</td>
      <td>Geração de documentação JavaDoc do projeto.</td>
    </tr>
    <tr>
      <td>Apache JMeter</td>
      <td>5.3.6</td>
      <td>Teste de desempenho.</td>
    </tr>
  <thead>
    <!-- Frontend -->
    <tr style="background-color:#2F4F4F; color:white;">
      <th colspan="3">Frontend / Interface Web</td></th>
    </tr>
  </thead>
    <tr>
      <td>Bootstrap (CSS)</td>
      <td>5.1.0</td>
      <td>Framework CSS para interfaces responsivas e componentes visuais.</td>
    </tr>
    <tr>
      <td>Open Iconic</td>
      <td>1.1.1</td>
      <td>Conjunto de ícones para interfaces web via WebJars.</td>
    </tr>
    <tr>
      <td>FontAwesome</td>
      <td>5.7.2</td>
      <td>Biblioteca de ícones escaláveis para frontend.</td>
    </tr>
    <tr>
      <td>jQuery</td>
      <td>3.3.1-1</td>
      <td>Manipulação de DOM, eventos e interatividade no frontend.</td>
    </tr>
    <tr>
      <td>jQuery Mask Plugin</td>
      <td>1.14.13</td>
      <td>Máscaras para campos de formulário (CEP, CPF, telefone).</td>
    </tr>
    <tr>
      <td>jQuery UI</td>
      <td>1.12.1</td>
      <td>Componentes de interface como autocomplete, draggable e datepicker.</td>
    </tr>
    <tr>
      <td>Popper.js</td>
      <td>1.12.9 / 1.14.7</td>
      <td>Posicionamento de tooltips, popovers e dropdowns.</td>
    </tr>
    <tr>
      <td>DataTables</td>
      <td>1.10.19</td>
      <td>Tabelas dinâmicas com paginação, ordenação e responsividade.</td>
    </tr>
    <tr>
      <td>DataTables Responsive</td>
      <td>2.2.1</td>
      <td>Adiciona responsividade às tabelas DataTables em dispositivos móveis.</td>
    </tr>
    <tr>
      <td>Moment.js</td>
      <td>2.22.2</td>
      <td>Manipulação e formatação de datas em JavaScript.</td>
    </tr>
    <tr>
      <td>Custom JS (projeto)</td>
      <td>Interno</td>
      <td>Funções específicas de negócios como CEP, validação de ano, máscaras e redirecionamento via WhatsApp.</td>
    </tr>
    <tr>
      <td>Custom CSS (projeto)</td>
      <td>Interno</td>
      <td>Estilização própria das páginas do sistema.</td>
    </tr>
  </tbody>
</table>

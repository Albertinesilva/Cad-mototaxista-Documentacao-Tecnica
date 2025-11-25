<h2 align="center">CAD-MOTOTAXISTA - Documentação Técnica</h2>

**Figura 1 – Mensagem de Violação de Integridade (Erro HTTP 400).**  
A imagem demonstra o retorno do servidor quando uma solicitação enviada pelo cliente viola regras de consistência ou restrições estabelecidas no backend, resultando em um erro **HTTP 400 – Bad Request**, indicando que a requisição é inválida para processamento.  
<img src="../../assets/img/autorizacao/Mensagem-de-Violacao-de-Integridade.jpg" alt="Mensagem de Violação de Integridade 400" width="100%" height="100%">  
**Fonte: Sistema CADMOTOTAXISTA (2025).**

---

**Figura 2 – Mensagem de Área Restrita ao acessar recurso protegido (Erro HTTP 403).**  
Esta evidência representa o bloqueio de acesso a recursos protegidos quando o usuário não possui as permissões necessárias. Nesse caso, o sistema retorna **HTTP 403 – Forbidden**, indicando que a requisição foi compreendida, porém não está autorizada.  
<img src="../../assets/img/autorizacao/Mensagem-Area-Restrita.png" alt="Mensagem de Área Restrita 403" width="100%" height="100%">  
**Fonte: Sistema CADMOTOTAXISTA (2025).**

---

**Figura 3 – Usuário inativo ou não encontrado (Erro HTTP 404).**  
A imagem evidencia o comportamento do sistema ao buscar informações de um usuário inexistente ou inativo. O servidor, ao não localizar o recurso solicitado, responde com **HTTP 404 – Not Found**, sinalizando ausência ou indisponibilidade do recurso.  
<img src="../../assets/img/autorizacao/Mensagem-Usuario-Inativo.png" alt="Usuário inativo ou não encontrado 404" width="100%" height="100%">  
**Fonte: Sistema CADMOTOTAXISTA (2025).**

---

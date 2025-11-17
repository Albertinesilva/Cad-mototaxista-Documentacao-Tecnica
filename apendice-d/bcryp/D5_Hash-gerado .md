<h2 align="center">CAD-MOTOTAXISTA - Documentação Técnica</h2>

## 🔐 Armazenamento Seguro de Senhas com BCrypt

<p align="justify">
O sistema <strong>CAD-MOTOTAXISTA</strong> utiliza o algoritmo <strong>BCrypt</strong> para realizar o hash das senhas antes de armazená-las no banco de dados, garantindo proteção contra ataques de força bruta e acessos indevidos. O BCrypt aplica automaticamente um <strong>salt</strong> aleatório e um número configurável de iterações (<strong>work factor</strong> ou <strong>cost</strong>), que aumenta exponencialmente o tempo necessário para gerar e validar um hash.
</p>

<p align="justify">
Neste projeto, foi adotado o valor cost = 10, configuração padrão recomendada pelo Spring Security, por oferecer um equilíbrio adequado entre segurança e desempenho. Conforme orientações da OWASP, fatores de custo mais elevados — como cost = 12 — reforçam a proteção contra ataques de força bruta, mas aumentam significativamente o tempo de processamento durante a autenticação, podendo impactar sistemas com alta demanda simultânea. Assim, a escolha do cost 10 mantém aderência às boas práticas de segurança sem comprometer a eficiência operacional <a href="https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html?utm_source=chatgpt.com">(OWASP, 2023)</a>.
</p> 

Para fins de demonstração técnica, a senha utilizada como exemplo é:

Teste@2025

A seguir, apresenta-se um hash ilustrativo gerado pelo BCrypt com **cost = 10**:

```bash
$2a$10$xmI9fc1RJyuUNy7/w1ni6OZteU0otqDs1hTAaRYlj/tFqmBASVP4G
```
O prefixo **`$2a$10$`** indica o uso do BCrypt com *work factor* 10, seguido pelo *salt* e pelo hash propriamente dito.  
⚠️ *O valor apresentado é meramente ilustrativo e não corresponde a nenhuma credencial real utilizada no sistema.*

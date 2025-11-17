<h2 align="center">CAD-MOTOTAXISTA - Documentação Técnica</h2>

## 🔐 Armazenamento Seguro de Senhas com BCrypt

O sistema **CADMOTOTAXISTA** utiliza o algoritmo **BCrypt** para realizar o hash das senhas antes de armazená-las no banco de dados, garantindo proteção contra ataques de força bruta e acessos indevidos. O BCrypt aplica automaticamente um *salt* aleatório e um número configurável de iterações (*work factor* ou *cost*), que aumenta exponencialmente o tempo necessário para gerar e validar um hash.

Neste projeto, foi adotado o valor **cost = 10**, configuração padrão recomendada pelo **Spring Security**, por oferecer um equilíbrio adequado entre segurança e desempenho. Embora valores superiores, como **cost = 12**, aumentem a resistência criptográfica, eles também elevam o tempo de processamento durante o login, podendo impactar ambientes com múltiplos acessos simultâneos. Assim, o cost 10 foi escolhido por garantir autenticação eficiente sem comprometer a proteção das credenciais.

Para fins de demonstração técnica, a senha utilizada como exemplo é:

Teste@2025

```bash

A seguir, apresenta-se um hash ilustrativo gerado pelo BCrypt com **cost = 10**:

$2a$10$xmI9fc1RJyuUNy7/w1ni6OZteU0otqDs1hTAaRYlj/tFqmBASVP4G


O prefixo **`$2a$10$`** indica o uso do BCrypt com *work factor* 10, seguido pelo *salt* e pelo hash propriamente dito.  
⚠️ *O valor apresentado é meramente ilustrativo e não corresponde a nenhuma credencial real utilizada no sistema.*
```
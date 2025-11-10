<h2 align="center">CadMototaxista - Documentação Técnica</h2>

### Regras de Negócio Sensíveis

Esta subseção apresenta um exemplo de regra crítica do sistema: a **verificação da validade do alvará** de mototaxistas e motofretistas.  
Essa regra garante que o sistema **impede a emissão ou renovação de alvarás vencidos**, assegurando a conformidade legal e a integridade dos dados.

A implementação utiliza a classe **LocalDate** da API de datas do Java, comparando a data de vencimento registrada com a data atual, retornando verdadeiro caso o alvará esteja vencido.

---
```java
/**
* Verifica se o alvará do condutor está vencido.
*
* @param condutor Condutor a ser verificado
* @return true se o alvará estiver vencido, false caso contrário
*/
private boolean isAlvaraVencido(Condutor condutor) {
  return Optional.ofNullable(condutor.getDataVencimentoAlvara())
          .map(data -> data.isBefore(LocalDate.now()))
          .orElse(false);
}
```

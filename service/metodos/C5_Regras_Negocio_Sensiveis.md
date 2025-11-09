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
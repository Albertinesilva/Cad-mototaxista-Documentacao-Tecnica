<h2 align="center">CadMototaxista - Documentação Técnica</h2>

### Integração com o Serviço Externo ViaCEP

Além das validações internas, o sistema incorpora rotinas de **integração com serviços externos** para aprimorar a experiência do usuário e garantir a consistência dos dados cadastrados.  

Um exemplo é a utilização do serviço público **ViaCEP**, que automatiza o preenchimento de endereços durante o cadastro de clientes, condutores e funcionários.

Principais funcionalidades do script JavaScript:
- Executa consultas assíncronas à API ViaCEP no lado do cliente;  
- Preenche automaticamente os campos de **logradouro, bairro e cidade** ao digitar um CEP válido;  
- Valida o formato do CEP e trata respostas incorretas ou ausentes, limpando os campos quando necessário;  
- Reduz erros de digitação e padroniza os dados, tornando o cadastro mais ágil e confiável.

---
```javaScript
/**
* Preenche automaticamente os campos de endereço ao digitar um CEP válido.
* A busca é feita assim que o campo atingir 8 dígitos (sem precisar sair do campo).
* Também limpa os campos se o CEP for inválido, incompleto ou apagado.
*/
function buscarEnderecoPorCep() {
  const cepInput = document.getElementById('cep');
  const logradouroInput = document.getElementById('logradouro');
  const bairroInput = document.getElementById('bairro');
  const cidadeInput = document.getElementById('cidade');

  if (!cepInput || !logradouroInput || !bairroInput || !cidadeInput) return;

  const limparCamposEndereco = () => {
    logradouroInput.value = '';
    bairroInput.value = '';
    cidadeInput.value = '';
  };

  cepInput.addEventListener('input', async function () {
    const cep = this.value.replace(/\D/g, '');

    if (cep.length === 8) {
        const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
        const data = await response.json();

        if (!data.erro) {
          logradouroInput.value = data.logradouro || '';
          bairroInput.value = data.bairro || '';
          cidadeInput.value = data.localidade || '';
        } else {
          alert("CEP não encontrado. Verifique o número digitado.");
        }
      } catch (error) {
        limparCamposEndereco();
        console.error('Erro ao buscar o CEP:', error);
        alert("Erro ao consultar o CEP. Tente novamente mais tarde.");
      }
    } else {
      limparCamposEndereco();
    }
  });
}
```

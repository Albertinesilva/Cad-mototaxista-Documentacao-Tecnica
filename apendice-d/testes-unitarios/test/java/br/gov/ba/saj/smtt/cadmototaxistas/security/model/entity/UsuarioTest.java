package br.gov.ba.saj.smtt.cadmototaxistas.security.model.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.gov.ba.saj.smtt.cadmototaxistas.security.model.entities.Perfil;
import br.gov.ba.saj.smtt.cadmototaxistas.security.model.entities.Usuario;
import br.gov.ba.saj.smtt.cadmototaxistas.security.model.enums.PerfilTipo;

public class UsuarioTest {

  @Test
  @DisplayName("Deve criar Usuario usando construtor padrão")
  void testConstrutorPadrao() {
    Usuario usuario = new Usuario();

    assertThat(usuario).isNotNull();
    assertThat(usuario.getId()).isNull();
  }

  @Test
  @DisplayName("Deve criar Usuario com ID usando construtor específico")
  void testConstrutorComId() {
    Usuario usuario = new Usuario(10L);

    assertThat(usuario.getId()).isEqualTo(10L);
  }

  @Test
  @DisplayName("Deve criar Usuario com email usando construtor específico")
  void testConstrutorComEmail() {
    Usuario usuario = new Usuario("user@test.com");

    assertThat(usuario.getEmail()).isEqualTo("user@test.com");
  }

  @Test
  @DisplayName("addPerfil deve inicializar lista e adicionar Perfil corretamente")
  void testAddPerfilCriandoLista() {
    Usuario usuario = new Usuario();

    usuario.addPerfil(PerfilTipo.ADMIN);

    assertThat(usuario.getPerfis()).isNotNull();
    assertThat(usuario.getPerfis()).hasSize(1);

    Perfil perfilAdicionado = usuario.getPerfis().get(0);

    // O Perfil foi criado com apenas o ID
    assertThat(perfilAdicionado.getId()).isEqualTo(PerfilTipo.ADMIN.getCod());
    assertThat(perfilAdicionado.getDescricao()).isNull(); // esperado
  }

  @Test
  @DisplayName("addPerfil deve adicionar Perfil em lista já existente")
  void testAddPerfilEmListaExistente() {
    Usuario usuario = new Usuario();
    usuario.setPerfis(new ArrayList<>());

    usuario.addPerfil(PerfilTipo.CLIENTE);

    assertThat(usuario.getPerfis()).hasSize(1);

    Perfil perfilAdicionado = usuario.getPerfis().get(0);

    assertThat(perfilAdicionado.getId()).isEqualTo(PerfilTipo.CLIENTE.getCod());
    assertThat(perfilAdicionado.getDescricao()).isNull();
  }

  @Test
  @DisplayName("Getter e Setter devem funcionar corretamente")
  void testGettersSetters() {
    Usuario usuario = new Usuario();

    usuario.setEmail("usuario@teste.com");
    usuario.setSenha("senha123");
    usuario.setAtivo(true);
    usuario.setCodigoVerificador("COD123");

    assertThat(usuario.getEmail()).isEqualTo("usuario@teste.com");
    assertThat(usuario.getSenha()).isEqualTo("senha123");
    assertThat(usuario.isAtivo()).isTrue();
    assertThat(usuario.getCodigoVerificador()).isEqualTo("COD123");
  }
}
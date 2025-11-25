package br.gov.ba.saj.smtt.cadmototaxistas.security.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import br.gov.ba.saj.smtt.cadmototaxistas.security.model.entities.Perfil;
import br.gov.ba.saj.smtt.cadmototaxistas.security.model.entities.Usuario;
import br.gov.ba.saj.smtt.cadmototaxistas.security.repositories.UsuarioRepository;
import br.gov.ba.saj.smtt.cadmototaxistas.security.repositories.PerfilRepository;

@DataJpaTest
@ActiveProfiles("test")
class UsuarioRepositoryTest {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private PerfilRepository perfilRepository;

  @Test
  void testFindByEmail() {
    Usuario u = new Usuario();
    u.setEmail("teste@teste.com");
    u.setSenha("$2a$10$/5.rdwAgc4Dwf3L0CaVr4u/.pBDFV.X0L8PBRXOEmE27FvRtNzAH2");
    u.setAtivo(true);

    usuarioRepository.save(u);

    Usuario result = usuarioRepository.findByEmail("teste@teste.com");

    assertThat(result).isNotNull();
    assertThat(result.getEmail()).isEqualTo("teste@teste.com");
  }

  @Test
  void testFindByEmailAndAtivo() {
    Usuario u = new Usuario();
    u.setEmail("ativo@teste.com");
    u.setSenha("$2a$10$/5.rdwAgc4Dwf3L0CaVr4u/.pBDFV.X0L8PBRXOEmE27FvRtNzAH2");
    u.setAtivo(true);

    usuarioRepository.save(u);

    Optional<Usuario> result = usuarioRepository.findByEmailAndAtivo("ativo@teste.com");

    assertThat(result).isPresent();
  }

  @Test
  void testFindByEmailOrPerfil() {

    // Cria e salva o perfil ADMIN
    Perfil perfilAdmin = new Perfil("ADMIN");
    perfilRepository.save(perfilAdmin);

    // Cria usuário
    Usuario u = new Usuario();
    u.setEmail("email@teste.com");
    u.setSenha("$2a$10$/5.rdwAgc4Dwf3L0CaVr4u/.pBDFV.X0L8PBRXOEmE27FvRtNzAH2");
    u.setAtivo(true);

    // Associa o perfil salvo
    u.setPerfis(List.of(perfilAdmin));

    usuarioRepository.save(u);

    Page<Usuario> page = usuarioRepository.findByEmailOrPerfil("email", PageRequest.of(0, 10));

    assertThat(page.getContent()).isNotEmpty();
    assertThat(page.getContent().get(0).getEmail()).isEqualTo("email@teste.com");
  }

  @Test
  void testFindByIdAndPerfis() {

    Perfil p = new Perfil("FUNCIONARIO");
    perfilRepository.save(p);

    Usuario u = new Usuario();
    u.setEmail("func@teste.com");
    u.setSenha("$2a$10$/5.rdwAgc4Dwf3L0CaVr4u/.pBDFV.X0L8PBRXOEmE27FvRtNzAH2");
    u.setAtivo(true);
    u.setPerfis(List.of(p));

    usuarioRepository.save(u);

    Optional<Usuario> result = usuarioRepository.findByIdAndPerfis(u.getId(), new Long[] { p.getId() });

    assertThat(result).isPresent();
  }

}
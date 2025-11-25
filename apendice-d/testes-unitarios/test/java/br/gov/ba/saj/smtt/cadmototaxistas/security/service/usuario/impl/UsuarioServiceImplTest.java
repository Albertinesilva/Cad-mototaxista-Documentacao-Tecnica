package br.gov.ba.saj.smtt.cadmototaxistas.security.service.usuario.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.mail.MessagingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.gov.ba.saj.smtt.cadmototaxistas.security.model.entities.Perfil;
import br.gov.ba.saj.smtt.cadmototaxistas.security.model.entities.Token;
import br.gov.ba.saj.smtt.cadmototaxistas.security.model.entities.Usuario;
import br.gov.ba.saj.smtt.cadmototaxistas.security.repositories.UsuarioRepository;
import br.gov.ba.saj.smtt.cadmototaxistas.datatables.Datatables;
import br.gov.ba.saj.smtt.cadmototaxistas.security.services.email.EmailService;
import br.gov.ba.saj.smtt.cadmototaxistas.security.services.token.TokenService;
import br.gov.ba.saj.smtt.cadmototaxistas.security.services.usuario.impl.UsuarioServiceImpl;

public class UsuarioServiceImplTest {

  @InjectMocks
  private UsuarioServiceImpl usuarioService;

  @Mock
  private UsuarioRepository usuarioRepository;

  @Mock
  private Datatables datatables;

  @Mock
  private EmailService emailService;

  @Mock
  private TokenService tokenService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testBuscarPorEmail() {
    Usuario u = new Usuario();
    u.setEmail("teste@dominio.com");

    when(usuarioRepository.findByEmail("teste@dominio.com"))
        .thenReturn(u);

    Usuario result = usuarioService.buscarPorEmail("teste@dominio.com");

    assertThat(result).isNotNull();
    assertThat(result.getEmail()).isEqualTo("teste@dominio.com");
  }

  @Test
  void testBuscarPorId() {
    Usuario u = new Usuario();
    u.setId(1L);

    when(usuarioRepository.findById(1L))
        .thenReturn(Optional.of(u));

    Usuario result = usuarioService.buscarPorId(1L);

    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(1L);
  }

  @Test
  void testBuscarTodos() {
    Usuario u1 = new Usuario();
    Usuario u2 = new Usuario();

    when(usuarioRepository.findAll())
        .thenReturn(List.of(u1, u2));

    List<Usuario> result = usuarioService.buscarTodos();

    assertThat(result).hasSize(2);
  }

  @Test
  void testSalvarUsuario() {
    Usuario u = new Usuario();
    u.setSenha("$2a$10$/5.rdwAgc4Dwf3L0CaVr4u/.pBDFV.X0L8PBRXOEmE27FvRtNzAH2");

    usuarioService.salvarUsuario(u);

    assertThat(u.getSenha()).isNotEqualTo("$2a$10$/5.rdwAgc4Dwf3L0CaVr4u/.pBDFV.X0L8PBRXOEmE27FvRtNzAH2");
    verify(usuarioRepository).save(u);
  }

  @Test
  void testEditar() {
    Usuario u = new Usuario();

    usuarioService.editar(u);

    verify(usuarioRepository).save(u);
  }

  @Test
  void testExcluir() {
    Usuario u = new Usuario();

    usuarioService.excluir(u);

    verify(usuarioRepository).delete(u);
  }

  @Test
  void testIsSenhaCorreta() {
    Usuario u = new Usuario();
    u.setSenha(new BCryptPasswordEncoder().encode("$2a$10$/5.rdwAgc4Dwf3L0CaVr4u/.pBDFV.X0L8PBRXOEmE27FvRtNzAH2"));

    boolean result = usuarioService.isSenhaCorreta("$2a$10$/5.rdwAgc4Dwf3L0CaVr4u/.pBDFV.X0L8PBRXOEmE27FvRtNzAH2",
        u.getSenha());

    assertThat(result).isTrue();
  }

  @Test
  void testValidarSenhaEObterUsuario() {
    Usuario u = new Usuario();
    u.setEmail("teste@dominio.com");
    u.setSenha(new BCryptPasswordEncoder().encode("$2a$10$/5.rdwAgc4Dwf3L0CaVr4u/.pBDFV.X0L8PBRXOEmE27FvRtNzAH2"));

    when(usuarioRepository.findByEmail("teste@dominio.com"))
        .thenReturn(u);

    User springUser = new User("teste@dominio.com", u.getSenha(), List.of());

    var result = usuarioService
        .validarSenhaEObterUsuario("$2a$10$/5.rdwAgc4Dwf3L0CaVr4u/.pBDFV.X0L8PBRXOEmE27FvRtNzAH2", springUser);

    assertThat(result).isPresent();
  }

  @Test
  void testLoadUserByUsername() {
    Usuario u = new Usuario();
    u.setPerfis(new ArrayList<>());
    u.setEmail("user@teste.com");
    u.setSenha(new BCryptPasswordEncoder().encode("$2a$10$/5.rdwAgc4Dwf3L0CaVr4u/.pBDFV.X0L8PBRXOEmE27FvRtNzAH2"));
    u.setAtivo(true);

    Perfil p = new Perfil();
    p.setDescricao("ROLE_FUNCIONARIO");
    u.getPerfis().add(p);

    when(usuarioRepository.findByEmailAndAtivo("user@teste.com"))
        .thenReturn(Optional.of(u));

    UserDetails ud = usuarioService.loadUserByUsername("user@teste.com");

    assertThat(ud.getUsername()).isEqualTo("user@teste.com");
    assertThat(ud.getPassword()).isEqualTo(u.getSenha());
    assertThat(ud.getAuthorities())
        .extracting("authority")
        .containsExactly("ROLE_FUNCIONARIO");
  }

  @Test
  void testSalvarUsuarioCliente() throws Exception {
    // Arrange
    Usuario usuario = new Usuario();
    usuario.setEmail("cliente@teste.com");
    usuario.setSenha("12345678AsJ@");

    when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
    when(tokenService.generateTokenAtivacao(any())).thenReturn("TOKEN123");

    // Act
    usuarioService.salvarUsuarioCliente(usuario);

    // Assert
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    assert encoder.matches("12345678AsJ@", usuario.getSenha());

    assert usuario.getPerfis() != null;
    assert usuario.getPerfis().size() == 1;

    verify(usuarioRepository).save(usuario);

    verify(emailService).enviarPedidoDeConfirmacaoDeCadastro(
        eq("cliente@teste.com"),
        eq("TOKEN123"));
  }

  @Test
  void testEmailDeConfirmacaoDeCadastro() throws MessagingException {
    // Arrange
    Usuario usuario = new Usuario();
    usuario.setEmail("email@teste.com");

    when(tokenService.generateTokenAtivacao(any())).thenReturn("CODIGO123");

    // Act
    usuarioService.emailDeConfirmacaoDeCadastro(usuario);

    // Assert
    verify(tokenService).generateTokenAtivacao(usuario);
    verify(emailService).enviarPedidoDeConfirmacaoDeCadastro("email@teste.com", "CODIGO123");
  }

  @Test
  void testPedidoRedefinicaoDeSenha_Sucesso() throws Exception {
    // Arrange
    Usuario usuario = new Usuario();
    usuario.setEmail("user@teste.com");
    usuario.setAtivo(true);
    usuario.setSenha("12345678AsJ@");

    Token token = new Token();
    token.setToken("TOK123");
    token.setUsuario(usuario);

    when(usuarioRepository.findByEmailAndAtivo("user@teste.com"))
        .thenReturn(Optional.of(usuario));

    when(tokenService.generateTokenParaRedefinicaoSenha(usuario))
        .thenReturn(token);

    when(tokenService.generateCodigoVerificador())
        .thenReturn("999999");

    // Act
    Token result = usuarioService.pedidoRedefinicaoDeSenha("user@teste.com");

    // Assert
    assertThat(result).isNotNull();
    assertThat(usuario.getCodigoVerificador()).isEqualTo("999999");

    verify(emailService).enviarPedidoDeRedefinicaoSenha(
        eq("user@teste.com"),
        eq("999999"));
  }

  @Test
  void testPedidoRedefinicaoDeSenha_UsuarioNaoEncontrado() {
    // Arrange
    when(usuarioRepository.findByEmailAndAtivo("user@teste.com"))
        .thenReturn(Optional.empty());

    // Act + Assert
    assertThrows(
        UsernameNotFoundException.class,
        () -> usuarioService.pedidoRedefinicaoDeSenha("user@teste.com"));
  }

  @Test
  void testProcessarRedefinicaoDeSenha_TokenInexistente() {
    when(tokenService.findByToken("XYZ")).thenReturn(Optional.empty());

    boolean result = usuarioService.processarRedefinicaoDeSenha("XYZ", new Usuario());

    assertThat(result).isFalse();
  }

  @Test
  void testProcessarRedefinicaoDeSenha_TokenExpirado() {
    Usuario usuario = new Usuario();
    usuario.setAtivo(true);
    usuario.setCodigoVerificador("12345678AsJ@");

    Token token = new Token();
    token.setUsuario(usuario);
    token.setDataExpiracao(Instant.now().minusSeconds(10)); // expirado

    when(tokenService.findByToken("AAA")).thenReturn(Optional.of(token));

    Usuario usuarioForm = new Usuario();
    usuarioForm.setCodigoVerificador("12345678AsJ@");

    boolean result = usuarioService.processarRedefinicaoDeSenha("AAA", usuarioForm);

    assertThat(result).isFalse();
  }

  @Test
  void testProcessarRedefinicaoDeSenha_TokenDesativado() {
    Usuario usuario = new Usuario();
    usuario.setAtivo(true);
    usuario.setCodigoVerificador("111111");

    Token token = new Token();
    token.setUsuario(usuario);
    token.disabled(); // token já usado
    token.setDataExpiracao(Instant.now().plusSeconds(60));

    when(tokenService.findByToken("T1")).thenReturn(Optional.of(token));

    Usuario usuarioForm = new Usuario();
    usuarioForm.setCodigoVerificador("111111");

    boolean result = usuarioService.processarRedefinicaoDeSenha("T1", usuarioForm);

    assertThat(result).isFalse();
  }

  @Test
  void testProcessarRedefinicaoDeSenha_CodigoVerificadorIncorreto() {
    Usuario usuario = new Usuario();
    usuario.setAtivo(true);
    usuario.setCodigoVerificador("ABC123");

    Token token = new Token();
    token.setUsuario(usuario);
    token.setDataExpiracao(Instant.now().plusSeconds(60));

    when(tokenService.findByToken("T2")).thenReturn(Optional.of(token));

    Usuario usuarioForm = new Usuario();
    usuarioForm.setCodigoVerificador("XYZ999");

    boolean result = usuarioService.processarRedefinicaoDeSenha("T2", usuarioForm);

    assertThat(result).isFalse();
  }

  @Test
  void testProcessarRedefinicaoDeSenha_UsuarioInativo() {
    Usuario usuario = new Usuario();
    usuario.setAtivo(false);
    usuario.setCodigoVerificador("123");

    Token token = new Token();
    token.setUsuario(usuario);
    token.setDataExpiracao(Instant.now().plusSeconds(60));

    when(tokenService.findByToken("AAA")).thenReturn(Optional.of(token));

    Usuario usuarioForm = new Usuario();
    usuarioForm.setCodigoVerificador("123");

    boolean result = usuarioService.processarRedefinicaoDeSenha("AAA", usuarioForm);

    assertThat(result).isFalse();
  }

  @Test
  void testProcessarRedefinicaoDeSenha_Sucesso() {
    // Arrange
    Usuario usuario = new Usuario();
    usuario.setAtivo(true);
    usuario.setCodigoVerificador("555666");

    Token token = new Token();
    token.setUsuario(usuario);
    token.setDataExpiracao(Instant.now().plusSeconds(60)); // válido

    when(tokenService.findByToken("OK")).thenReturn(Optional.of(token));

    Usuario usuarioForm = new Usuario();
    usuarioForm.setCodigoVerificador("555666");
    usuarioForm.setSenha("novaSenha123");

    // Act
    boolean result = usuarioService.processarRedefinicaoDeSenha("OK", usuarioForm);

    // Assert
    assertThat(result).isTrue();
    assertThat(usuario.getCodigoVerificador()).isNull(); // código removido

    verify(tokenService).salvar(token); // token desativado
    assertThat(usuario.getSenha()).isNotEqualTo("novaSenha123"); // deve estar criptografada
  }

}
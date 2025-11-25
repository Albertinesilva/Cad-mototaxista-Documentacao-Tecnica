package br.gov.ba.saj.smtt.cadmototaxistas.security.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;

import br.gov.ba.saj.smtt.cadmototaxistas.security.model.entities.Token;
import br.gov.ba.saj.smtt.cadmototaxistas.security.model.entities.Usuario;
import br.gov.ba.saj.smtt.cadmototaxistas.security.model.enums.PerfilTipo;
import br.gov.ba.saj.smtt.cadmototaxistas.security.services.usuario.UsuarioService;
import br.gov.ba.saj.smtt.cadmototaxistas.service.funcionario.FuncionarioService;
import br.gov.ba.saj.smtt.cadmototaxistas.util.enums.ResultadoAtivacao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UsuarioController.class)
@AutoConfigureMockMvc(addFilters = false)
class UsuarioControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UsuarioService usuarioService;

  @MockitoBean
  private FuncionarioService funcionarioService;

  // ======== TESTES SIMPLES DE GET =========

  @Test
  void deveAbrirTelaCadastroUsuarioAdmin() throws Exception {
    mockMvc.perform(get("/u/novo/cadastro/usuario"))
        .andExpect(status().isOk())
        .andExpect(view().name("usuario/cadastro"));
  }

  @Test
  void deveAbrirListaUsuarios() throws Exception {
    mockMvc.perform(get("/u/lista"))
        .andExpect(status().isOk())
        .andExpect(view().name("usuario/lista"));
  }

  @Test
  void deveAbrirTelaEditarSenha() throws Exception {
    mockMvc.perform(get("/u/editar/senha"))
        .andExpect(status().isOk())
        .andExpect(view().name("usuario/editar-senha"));
  }

  @Test
  void deveAbrirNovoCadastroCliente() throws Exception {
    mockMvc.perform(get("/u/novo/cadastro"))
        .andExpect(status().isOk())
        .andExpect(view().name("cadastrar-se"));
  }

  @Test
  void deveRetornarMensagemCadastroRealizado() throws Exception {
    mockMvc.perform(get("/u/cadastro/realizado"))
        .andExpect(status().isOk())
        .andExpect(view().name("fragments/mensagem"));
  }

  @Test
  void deveAbrirTelaPedidoRedefinirSenha() throws Exception {
    mockMvc.perform(get("/u/p/redefinir/senha"))
        .andExpect(status().isOk())
        .andExpect(view().name("usuario/pedido-recuperar-senha"));
  }

  @Test
  void deveSalvarClienteComSucesso() throws Exception {

    doNothing().when(usuarioService).salvarUsuarioCliente(any(Usuario.class));

    mockMvc.perform(post("/u/cadastro/cliente/salvar")
        .param("email", "joao@mail.com")
        .with(csrf()))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/u/cadastro/realizado"));

    verify(usuarioService, times(1)).salvarUsuarioCliente(any(Usuario.class));
  }

  @Test
  void deveSalvarClienteComErroValidacao() throws Exception {

    mockMvc.perform(post("/u/cadastro/cliente/salvar")
        .param("email", "teste@teste.com")
        .param("senha", "") // inválido
        .with(csrf()))
        .andExpect(status().isOk()) // 200
        .andExpect(model().attributeHasFieldErrors("usuario", "senha"))
        .andExpect(view().name("cadastrar-se"));

    verify(usuarioService, never()).salvarUsuarioCliente(any());

  }

  @Test
  void deveRetornarErroQuandoEmailDuplicado() throws Exception {

    doThrow(DataIntegrityViolationException.class)
        .when(usuarioService).salvarUsuarioCliente(any(Usuario.class));

    mockMvc.perform(post("/u/cadastro/cliente/salvar")
        .param("email", "existente@mail.com")
        .param("senha", "12345678AsJ@")
        .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(view().name("cadastrar-se"))
        .andExpect(model().attributeHasFieldErrors("usuario", "email"));

    verify(usuarioService, times(1)).salvarUsuarioCliente(any(Usuario.class));
  }

  @Test
  void deveRetornarErroValidacaoCadastroAdmin() throws Exception {

    mockMvc.perform(post("/u/cadastro/salvar")
        .param("email", "") // inválido
        .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(view().name("usuario/cadastro"))
        .andExpect(model().attributeHasFieldErrors("usuario", "email"));

    verify(usuarioService, never()).salvarUsuario(any());
  }

  @Test
  void deveRetornarErroCadastroAdminEmailDuplicado() throws Exception {

    doThrow(DataIntegrityViolationException.class)
        .when(usuarioService).salvarUsuario(any());

    mockMvc.perform(post("/u/cadastro/salvar")
        .param("email", "admin@mail.com")
        .param("senha", "12345678AsJ@")
        .with(csrf()))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/u/novo/cadastro/usuario"));

    verify(usuarioService, times(1)).salvarUsuario(any());
  }

  @Test
  void deveRetornarErroQuandoSenhasNaoConferem() throws Exception {

    mockMvc.perform(post("/u/confirmar/senha")
        .param("senha1", "abc")
        .param("senha2", "12345678AsJ@") // diferente
        .param("senha3", "senhaAtual")
        .with(csrf()))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/u/editar/senha"));

    verify(usuarioService, never()).alterarSenha(any(), anyString());
  }

  // Falta testar daqui pra baixo ----
  @Test
  void deveRetornarErroQuandoSenhaAtualNaoConfere() throws Exception {

    User usuarioSpring = new User(
        "email@mail.com",
        "12345678AsJ@",
        new ArrayList<>());

    Usuario usuario = new Usuario();
    usuario.setEmail("email@mail.com");
    usuario.setSenha("12345678AsJ@"); // senha armazenada no banco (mock)

    when(usuarioService.buscarPorEmail("email@mail.com"))
        .thenReturn(usuario);

    // Mock correto — método recebe (String digitada, String armazenada)
    when(usuarioService.isSenhaCorreta(anyString(), anyString()))
        .thenReturn(false);

    mockMvc.perform(post("/u/confirmar/senha")
        .param("senha1", "12345678AsJ@")
        .param("senha2", "12345678AsJ@")
        .param("senha3", "senhaIncorreta") // senha atual errada
        .with(csrf())
        .principal(new UsernamePasswordAuthenticationToken(
            usuarioSpring,
            usuarioSpring.getPassword(),
            usuarioSpring.getAuthorities())))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/u/editar/senha"));

    // Não deve alterar a senha se isSenhaCorreta → false
    verify(usuarioService, never()).alterarSenha(any(), anyString());
  }

  @Test
  void deveAlterarSenhaComSucesso() throws Exception {

    User usuarioSpring = new User("email@mail.com", "x", new ArrayList<>());

    Usuario usuario = new Usuario();
    usuario.setEmail("email@mail.com");

    when(usuarioService.buscarPorEmail("email@mail.com")).thenReturn(usuario);
    when(usuarioService.isSenhaCorreta(any(), any())).thenReturn(true);

    mockMvc.perform(post("/u/confirmar/senha")
        .param("senha1", "nova")
        .param("senha2", "nova")
        .param("senha3", "senhaAtual")
        .with(csrf())
        .principal(() -> "email@mail.com"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/u/editar/senha"));

    verify(usuarioService, times(1)).alterarSenha(eq(usuario), eq("nova"));
  }

  @Test
  void deveConfirmarCadastroClienteComSucesso() throws Exception {

    when(usuarioService.ativarCadastroCliente("abc"))
        .thenReturn(ResultadoAtivacao.ATIVADO);

    mockMvc.perform(get("/u/confirmacao/cadastro")
        .param("codigo", "abc"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/login"));
  }

  @Test
  void deveRetornarErroTokenInvalido() throws Exception {

    when(usuarioService.ativarCadastroCliente("xxx"))
        .thenReturn(ResultadoAtivacao.TOKEN_INVALIDO);

    mockMvc.perform(get("/u/confirmacao/cadastro")
        .param("codigo", "xxx"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/login"));
  }

  @Test
  void devePedirRedefinicaoSenha() throws Exception {

    Token token = new Token();
    token.setToken("123");
    when(usuarioService.pedidoRedefinicaoDeSenha("email@mail.com"))
        .thenReturn(token);

    mockMvc.perform(get("/u/p/recuperar/senha")
        .param("email", "email@mail.com"))
        .andExpect(status().isOk())
        .andExpect(view().name("usuario/recuperar-senha"))
        .andExpect(model().attributeExists("usuario"))
        .andExpect(model().attributeExists("token"));
  }

  @Test
  void deveRetornarErroSeTokenInvalidoNaNovaSenha() throws Exception {

    when(usuarioService.processarRedefinicaoDeSenha(any(), any()))
        .thenReturn(false);

    mockMvc.perform(post("/u/p/nova/senha")
        .param("token", "xxx")
        .param("usuario.email", "a@mail.com")
        .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(view().name("usuario/recuperar-senha"))
        .andExpect(model().attributeExists("falha"));
  }

  @Test
  void deveRedefinirSenhaComSucesso() throws Exception {

    when(usuarioService.processarRedefinicaoDeSenha(any(), any()))
        .thenReturn(true);

    mockMvc.perform(post("/u/p/nova/senha")
        .param("token", "123")
        .param("usuario.email", "a@mail.com")
        .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(view().name("login"))
        .andExpect(model().attributeExists("alerta"));
  }

  @Test
  void devePreEditarCredenciais() throws Exception {

    Usuario usuario = new Usuario();
    usuario.setId(1L);

    when(usuarioService.buscarPorId(1L)).thenReturn(usuario);

    mockMvc.perform(get("/u/editar/credenciais/usuario/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("usuario/cadastro"))
        .andExpect(model().attributeExists("usuario"));
  }

  @Test
  void deveRetornar403QuandoClienteTentaEditarOutroCliente() throws Exception {

    Usuario usuario = new Usuario();
    usuario.addPerfil(PerfilTipo.CLIENTE);

    when(usuarioService.buscarPorIdEPerfis(eq(1L), any())).thenReturn(usuario);

    mockMvc.perform(get("/u/editar/dados/usuario/1/perfis/3"))
        .andExpect(status().isOk())
        .andExpect(view().name("error"))
        .andExpect(model().attribute("status", 403));
  }

}
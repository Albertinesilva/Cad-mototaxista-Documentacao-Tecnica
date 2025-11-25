<h2 align="center">CAD-MOTOTAXISTA - Documentação Técnica</h2>

<br>

### Trechos de Segurança e Autenticação

O sistema **CADMOTOTAXISTA** utiliza **Spring Security** para gerenciar autenticação, autorização e controle de sessões de usuários.

Principais responsabilidades:

- Definir permissões de acesso conforme os perfis de usuário: **Administrador, Funcionário e Cliente**;
- Configurar regras para páginas públicas e restritas, controle de login, logout e expiração de sessão;
- Implementar política de “lembrar-me” para sessões persistentes;
- Codificar senhas utilizando **BCrypt**;
- Manter registro de sessões ativas, garantindo rastreabilidade e segurança dos acessos.

---

```java
package br.gov.ba.saj.smtt.cadmototaxistas.security.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import br.gov.ba.saj.smtt.cadmototaxistas.security.model.enums.PerfilTipo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

        @Value("${app.security.remember-me-key}")
        private String rememberMeKey;

        private static final String ADMIN = PerfilTipo.ADMIN.getDescricao();
        private static final String FUNCIONARIO = PerfilTipo.FUNCIONARIO.getDescricao();
        private static final String CLIENTE = PerfilTipo.CLIENTE.getDescricao();

        @Bean
        public SecurityFilterChain configure(HttpSecurity http) throws Exception {

                http.authorizeHttpRequests((authorize) -> authorize
                                // Permite acesso público a arquivos estáticos
                                .requestMatchers("/webjars/**", "/css/**", "/image/**", "/js/**").permitAll()
                                .requestMatchers("/", "/home", "/mensagens-alvara", "/expired").permitAll()
                                .requestMatchers("/u/novo/cadastro", "/u/cadastro/realizado","/u/cadastro/cliente/salvar").permitAll()
                                .requestMatchers("/u/confirmacao/cadastro").permitAll()
                                .requestMatchers("/u/p/**").permitAll()

                                // acessos privados admin
                                .requestMatchers("/u/editar/senha", "/u/confirmar/senha")
                                .hasAnyAuthority(FUNCIONARIO, CLIENTE)
                                .requestMatchers("/u/**").hasAuthority(ADMIN)
                                .requestMatchers("/condutores/**").hasAnyAuthority(ADMIN, FUNCIONARIO)
                                .requestMatchers("/auditorias/**").hasAuthority(ADMIN)
                                .requestMatchers("/relatorios/**").hasAuthority(ADMIN)

                                // Permissões para Clientes
                                .requestMatchers("/clientes/**").hasAuthority(CLIENTE)

                                // acessos privados funcionários
                                .requestMatchers("/funcionarios/dados", "/funcionarios/salvar", "/funcionarios/editar")
                                .hasAnyAuthority(FUNCIONARIO, ADMIN)
                                .requestMatchers("/funcionarios/**").hasAuthority(FUNCIONARIO)
                                .requestMatchers("/condutores/**").hasAnyAuthority(FUNCIONARIO, ADMIN)

                                .anyRequest().authenticated() // Restringe acesso a rotas não especificadas
                )

                                // Configuração de login
                                .formLogin(formLogin -> formLogin
                                                .loginPage("/login") // Página customizada de login
                                                .defaultSuccessUrl("/", true) // Redireciona após login bem-sucedido
                                                .failureUrl("/login-error") // Redireciona em caso de falha
                                                .permitAll() // Permite acesso a todos os usuários
                                )
                                .csrf(csrf -> csrf.ignoringRequestMatchers("/clientes/registrar-contato"))
                                // Configuração de logout
                                .logout(logout -> logout
                                                .logoutSuccessUrl("/") // Redireciona para home após logout
                                                .invalidateHttpSession(true) // Invalida sessão após logout
                                                .deleteCookies("JSESSIONID")// Remove cookies de sessão
                                )
                                .rememberMe(withDefaults())

                                // Tratamento de acessos negados
                                .exceptionHandling(exception -> exception
                                                .accessDeniedPage("/acesso-negado") // Página customizada de acesso
                                                                                    // negado
                                )
                                // Configuração de sessão e cookies de autenticação
                                .rememberMe(rememberMe -> rememberMe
                                                .key(rememberMeKey) // Opcional, define uma chave única
                                                .tokenValiditySeconds(86400) // Tempo de validade do token (1 dia)
                                )
                                // Configuração de sessão (separada de rememberMe)
                                .sessionManagement((session) -> session
                                                .maximumSessions(1) // Número máximo de sessões
                                                .expiredUrl("/expired") // Página de sessão expirada
                                                .maxSessionsPreventsLogin(false) // Permite login simultâneo
                                                .sessionRegistry(sessionRegistry()) // Registra sessões ativas
                                )
                                // Configuração de sessão (separada de rememberMe)
                                .sessionManagement((session) -> session
                                                .sessionFixation() // Proteção contra ataques de fixação de sessão
                                                .newSession() // Cria uma nova sessão
                                                .sessionAuthenticationStrategy(sessionAuthStrategy()) // Estratégia de
                                                                                                      // autenticação de
                                                                                                      // sessão
                                );

                return http.build();
        }

        /**
         * Define um bean para codificação de senhas usando
         * {@link BCryptPasswordEncoder}.
         * <p>
         * O {@code BCryptPasswordEncoder} gera um hash seguro e é recomendado para
         * armazenamento de senhas no banco de dados.
         * </p>
         *
         * @return Uma instância de {@link PasswordEncoder} que usa o algoritmo BCrypt.
         * @see BCryptPasswordEncoder
         */
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        /**
         * Define um bean para o {@link AuthenticationManager}, utilizando a
         * configuração automática do Spring Security.
         * <p>
         * Este método obtém e retorna a instância gerenciada de {@link AuthenticationManager}
         * a partir de {@link AuthenticationConfiguration}, permitindo que o Spring descubra automaticamente
         * a implementação de {@link UserDetailsService} presente na aplicação.
         * </p>
         *
         * @param authenticationConfiguration configuração de autenticação do Spring Security.
         * @return uma instância de {@link AuthenticationManager} gerenciada pelo Spring.
         * @throws Exception caso ocorra um erro ao obter o {@link AuthenticationManager}.
         */
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        /**
         * Estratégia de autenticação de sessão que registra sessões autenticadas.
         *
         * @return {@link SessionAuthenticationStrategy} configurada.
         */
        @Bean
        public SessionAuthenticationStrategy sessionAuthStrategy() {
                return new RegisterSessionAuthenticationStrategy(sessionRegistry());
        }

        /**
         * Registro de sessões ativas na aplicação.
         *
         * @return {@link SessionRegistry} para controle de sessões.
         */
        @Bean
        public SessionRegistry sessionRegistry() {
                return new SessionRegistryImpl();
        }

        /**
         * Registra o {@link HttpSessionEventPublisher} para gerenciamento de sessões HTTP.
         *
         * @return Bean de listener de eventos de sessão HTTP.
         */
        @Bean
        public ServletListenerRegistrationBean<?> servletListenerRegistrationBean() {
                return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
        }

}
```

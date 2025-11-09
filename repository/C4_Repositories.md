```java
package br.gov.ba.saj.smtt.cadmototaxistas.security.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.ba.saj.smtt.cadmototaxistas.security.model.entities.Usuario;

/**
 * Repositório para operações de persistência relacionadas à entidade
 * {@link Usuario}.
 *
 * <p>
 * Fornece métodos para busca e paginação de usuários com base em email e
 * perfis.
 * @author: Albert Silva
 * </p>
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	/**
	 * Busca um usuário pelo email.
	 *
	 * @param email o email do usuário
	 * @return o usuário com o email informado, ou {@code null} se não existir
	 */
	@Query("select u from Usuario u where u.email like :email")
	Usuario findByEmail(@Param("email") String email);

	/**
	 * Busca usuários cujo email ou descrição do perfil iniciem com o texto de busca,
	 * retornando os resultados paginados.
	 *
	 * @param search   texto para busca no email ou descrição do perfil
	 * @param pageable objeto de paginação
	 * @return página contendo usuários que atendem aos critérios
	 */
	@Query("select distinct u from Usuario u "
			+ "join u.perfis p "
			+ "where u.email like :search% OR p.descricao like :search%")
	Page<Usuario> findByEmailOrPerfil(String search, Pageable pageable);

	/**
	 * Busca um usuário por seu ID e que possua pelo menos um dos perfis informados.
	 *
	 * @param usuarioId ID do usuário
	 * @param perfisId  array de IDs de perfis
	 * @return um {@link Optional} contendo o usuário, se encontrado
	 */
	@Query("select u from Usuario u "
			+ "join u.perfis p "
			+ "where u.id = :usuarioId AND p.id IN :perfisId")
	Optional<Usuario> findByIdAndPerfis(Long usuarioId, Long[] perfisId);

	/**
	 * Busca um usuário ativo pelo email.
	 *
	 * @param email email do usuário
	 * @return um {@link Optional} contendo o usuário ativo, se encontrado
	 */
	@Query("select u from Usuario u where u.email like :email AND u.ativo = true")
	Optional<Usuario> findByEmailAndAtivo(String email);

}
```

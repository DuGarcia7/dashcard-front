package br.com.carloscorp.dashcard.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.carloscorp.dashcard.model.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, Integer>{
	/* este método segue a estratégia QueryByMethodName, ou seja, se seguirmos o padrão JPA
	 * ele irá decodificar da seguinte maneira:
	 * 
	 */
	public Usuario findByEmailAndSenha(String email, String senha);

	public Usuario findByEmailOrRacf(String email, String racf);
}

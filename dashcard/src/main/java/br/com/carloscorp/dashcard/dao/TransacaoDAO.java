package br.com.carloscorp.dashcard.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.carloscorp.dashcard.dto.ConsolidadoStatus;
import br.com.carloscorp.dashcard.model.Transacao;

public interface TransacaoDAO extends CrudRepository<Transacao, Integer> {
	
	//estou fazendo aqui pq minha fonte de dados principal é a de transações
	//@Query(value = "", nativeQuery = true)
	@Query("SELECT new br.com.carloscorp.dashcard.dto.ConsolidadoStatus"
			+ "(t.agente.nome, t.agente.volume, t.status, count(t.status))" 
			+ "FROM Transacao t WHERE t.agente.id=:id GROUP BY t.status")
	public ArrayList<ConsolidadoStatus> recuperarStatus(@Param("id") int id);
	
}

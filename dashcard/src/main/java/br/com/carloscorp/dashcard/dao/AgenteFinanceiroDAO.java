package br.com.carloscorp.dashcard.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.carloscorp.dashcard.model.AgenteFinanceiro;

//CrudRepository só pode ser importado em interfaces
//Crud = Create, Read, Update, Delete
//abaixo, entre <> colocar o nome da classe criada no model e o tipo da chave primaria
public interface AgenteFinanceiroDAO extends CrudRepository<AgenteFinanceiro, Integer> {
	
	public ArrayList<AgenteFinanceiro> findAllByOrderByVolumeDesc();
	
}

package br.com.carloscorp.dashcard.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.carloscorp.dashcard.dao.TransacaoDAO;
import br.com.carloscorp.dashcard.dto.ConsolidadoStatus;
import br.com.carloscorp.dashcard.model.Transacao;

@RestController
@CrossOrigin("*")
public class TransacaoController {
	
	@Autowired
	TransacaoDAO dao;
	
	@GetMapping("/transacoes")
	public ArrayList<Transacao> obterTodas(){
		ArrayList <Transacao> lista;
		lista = (ArrayList<Transacao>)dao.findAll();
		return lista;
	}
	
	@GetMapping("/totaisconsolidados")
	public ArrayList<ConsolidadoStatus> recuperarConsolidado(@RequestParam(name="id") int id){
		ArrayList<ConsolidadoStatus> lista = null;
		lista = dao.recuperarStatus(id);
		return lista;
	}

	
	
}

package br.com.carloscorp.dashcard.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carloscorp.dashcard.dao.AgenteFinanceiroDAO;
import br.com.carloscorp.dashcard.model.AgenteFinanceiro;

@RestController
@CrossOrigin("*") // para aceitar acesso de outras máquinas
public class AgenteFinanceiroController {
	
	@Autowired
	private AgenteFinanceiroDAO dao;
	
	@GetMapping("/agentes")
	public ArrayList<AgenteFinanceiro> rescuperarTodos(){
		ArrayList <AgenteFinanceiro> lista;
		lista = dao.findAllByOrderByVolumeDesc();
		return lista;
	}	

}

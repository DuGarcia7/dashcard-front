package br.com.carloscorp.dashcard.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.carloscorp.dashcard.dao.UsuarioDAO;
import br.com.carloscorp.dashcard.model.Usuario;

@RestController
@CrossOrigin("*") // para aceitar acesso de outras máquinas
public class UsuarioController {
	
	/* A anotação autowired realiza a injeção de dependência, isto é,
	 * basicamente delegamos a instanciação do objeto para a mq virtual (em outras palavras, não precisamos dar NEW)
	 */
	
	
	@Autowired
	UsuarioDAO dao;
	
	@GetMapping("/todos")
	public ArrayList<Usuario> recuperarTodos(){
		ArrayList<Usuario> lista;
		lista = (ArrayList<Usuario>)dao.findAll();
		return lista;		
	}
	
	//PostMapping é para enviar os dados de forma segura
	//GetMapping só permite enviar via URL, o que não é seguro. Ex:http://localhost:8088/testeuser?email="isidro@isidro.com"&senha="1234"
	/*@PostMapping("/testeuser")
	public String testandoUsuario(@RequestBody Usuario dadosLogin) {
		Usuario u = dao.findByEmailAndSenha(dadosLogin.getEmail(), dadosLogin.getSenha());
		if (u != null) {
			return "Usuário encontrado: "+u.getNome();
		}
		else {
			return "Usuário não encontrado";
		}
	}*/
	
	@PostMapping("/login")
	public ResponseEntity<Usuario> testandoUsuario(@RequestBody Usuario dadosLogin) {
		/* Response Entity retorna os códigos 200, 401, 404... 
		 * passo 1 - recuperar usuario por email ou racf
		 * passo 2 - se o usuario nao exisitir, retorno codigo 404 (Not found)
		 * passo 3 - se ele existir, vou conferir a senha
		 * passo 4 - se a senha não coincidir, retorno codigo 401 (Unauthorized)
		 * passo 5 - usuario existe e a senha coincide, retorno 200
		 */
		Usuario resultado = dao.findByEmailOrRacf(dadosLogin.getEmail(), dadosLogin.getRacf());
		if (resultado != null) {  // usuario existe
			// vou conferir a senha
			if (resultado.getSenha().equals(dadosLogin.getSenha())){
				return ResponseEntity.ok(resultado);
			}
			else {
				return ResponseEntity.status(401).build();
			}
		}
		else {
			return ResponseEntity.notFound().build();
		}
				
	}
}
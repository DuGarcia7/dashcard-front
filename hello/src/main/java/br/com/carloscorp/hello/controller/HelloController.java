package br.com.carloscorp.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carloscorp.hello.model.Produto;

@RestController //comando que deixa vc publicar na web. Digitar e apertar ctrl shift O
public class HelloController {
	
	@GetMapping ("/hello") //você faz com que uma url seja associada ao seguinte método
	public String sayHello() {
		return "Hello World";
	}
	
	@GetMapping("/produto")
	public Produto mostrarProduto() {
		Produto p = new Produto();
		p.setId(1234);
		p.setDescricao("Computador top of mind super hiper mega daora");
		p.setPreco(2872.43);
		return p;
	}
}

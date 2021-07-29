package org.generation.blogPessoal.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	private Usuario usuarioSalvar;
	private Usuario usuario;
	private Usuario usuarioAlterar;
	
	@BeforeAll
	public void start() {
		usuarioSalvar = new Usuario("FELIPE","felipe@gmail", "123456");
		usuario = new Usuario("FELIPE", "123456");
		usuarioAlterar = new Usuario("FELIPE", "111111");
	}

	
	//@Test
	void deveSalvarUsuarioRetornaStatus201() {
		
		/*
		 * Criando um objeto do tipo HttpEntity para enviar como terceiro
		 * parâmentro do método exchange. (Enviando um objeto usuario via body)
		 * 
		 * */
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioSalvar);
		
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuario/novo", HttpMethod.POST, request, Usuario.class);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}
	
	
	
	//@Test
	void deveAtualizarSenhaUsuarioRetornaStatus201() {
		
		/*
		 * Criando um objeto do tipo HttpEntity para enviar como terceiro
		 * parâmentro do método exchange. (Enviando um objeto usuario via body)
		 * 
		 * */
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioAlterar);
		
		ResponseEntity<Usuario> resposta = testRestTemplate.withBasicAuth("felipe@gmail","134652")
				.exchange("/usuario/alterar", HttpMethod.PUT, request, Usuario.class);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}
	
	
}

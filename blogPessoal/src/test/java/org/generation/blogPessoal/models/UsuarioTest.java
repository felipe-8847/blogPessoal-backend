package org.generation.blogPessoal.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class UsuarioTest {

public Usuario usuario;
	
	@Autowired
	private  ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	
	
	@BeforeEach
	public void start() {
		usuario = new Usuario("felipe1", "FELIPE1@teste", "1234567");
	}
	
	//@Test
	void testValidaAtributos() {
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuario);
			assertTrue(violacao.isEmpty());
	}
	
	//@Test
	void testValidaAtributosNulos() {
		Usuario usuarioErro = new Usuario();
		usuarioErro.setUsuario("Yuri Boaz");
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioErro);
		assertTrue(violacao.isEmpty());
	}
}

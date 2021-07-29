package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repositorioU;
	
	@BeforeAll
	void start() {
		Usuario usuario = new Usuario("VagnerBoaz","V@teste", "134652");
		if(repositorioU.findByUsuario(usuario.getUsuario())!=null)
			repositorioU.save(usuario);
		
		usuario = new Usuario("LucasBoaz","l@teste", "134652");
		if(repositorioU.findByUsuario(usuario.getUsuario())!=null)
			repositorioU.save(usuario);
		
		usuario = new Usuario("MarceloBoaz","m@teste", "134652");
		if(repositorioU.findByUsuario(usuario.getUsuario())!=null)
			repositorioU.save(usuario);
	}
	
	//@Test
	public void findByUsuarioRetornaUsuario() throws Exception {

		Usuario usuario = repositorioU.findByUsuario("VagnerBoaz").get();
		assertTrue(usuario.getUsuario().equals("VagnerBoaz"));
	}
	
	//@Test
	public void findAllByUsuarioContainingIgnoreCaseRetornaTresContato() {

		List<Usuario> listaDeUsuarios = repositorioU.findAllByUsuarioContainingIgnoreCase("Boaz");
		assertEquals(3, listaDeUsuarios.size());
	}
	
	@AfterAll
	public void end() {
		repositorioU.deleteAll();
	}
}

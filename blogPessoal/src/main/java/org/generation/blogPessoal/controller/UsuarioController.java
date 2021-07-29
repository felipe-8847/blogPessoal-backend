package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.model.DTO.UserLoginDTO;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

	private @Autowired UsuarioRepository repository;
	private @Autowired UsuarioServices services;

	@GetMapping("/todos")
	public ResponseEntity<List<Usuario>> pegarTodosUsuario() {
		List<Usuario> listaDeUsuarios = repository.findAll();
		if (!listaDeUsuarios.isEmpty()) {
			return ResponseEntity.status(200).body(listaDeUsuarios);
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@GetMapping("/id/{id_Usuario}")
	public ResponseEntity<Usuario> pegarUsuarioPorId(@PathVariable(value = "id_Usuario") Long idUsuario) {
		return repository.findById(idUsuario).map(usuarioExistente -> ResponseEntity.status(200).body(usuarioExistente))
				.orElse(ResponseEntity.status(204).build());
	}

	@PostMapping("/novo")
	public ResponseEntity<Usuario> cadastrarNovoUsuario(@Valid @RequestBody Usuario usuario) {
		return services.cadastrarUsuario(usuario)
				.map(usuarioCadastrado -> ResponseEntity.status(201).body(usuarioCadastrado))
				.orElse(ResponseEntity.status(400).build());
	}

	@PostMapping("/login")
	public ResponseEntity<UserLoginDTO> autorizarELogarUsuario(@Valid @RequestBody Optional<UserLoginDTO> usuarioLogin) {
		return services.logarUsuario(usuarioLogin)
				.map(usuarioAutorizado -> ResponseEntity.status(202).body(usuarioAutorizado))
				.orElse(ResponseEntity.status(401).build());
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> atualizarUsuario(@Valid @RequestBody Usuario usuarioParaAtualizar) {
		return repository.findByUsuario(usuarioParaAtualizar.getUsuario()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(usuarioParaAtualizar.getSenha());
			usuarioExistente.setSenha(senhaCriptografada);
			usuarioExistente.setNome(usuarioParaAtualizar.getNome());
			return ResponseEntity.status(201).body(repository.save(usuarioExistente));
		}).orElse(ResponseEntity.status(400).build());
	}
	
	@DeleteMapping("/{id}")
	public void deletarUsuario(@Valid @PathVariable Long id) {
		repository.deleteById(id);
	}
}

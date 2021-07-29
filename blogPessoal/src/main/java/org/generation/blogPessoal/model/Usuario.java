package org.generation.blogPessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@NotNull(message = "O nome não pode ser nulo!")
	@Size(min = 2, max = 100)
	private String nome;

	@NotNull(message = "O usuario não pode ser nulo")
	@Size(min = 2, max = 200)
	private String usuario;

	@NotNull(message = "A senha não pode ser nula e conter no minimo 6 digitos")
	@Size(min = 6, max = 100)
	private String senha;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoDeUsuario tipo;
	
	private String urlFoto;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;
	
	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

	public Usuario() {

	}

	public Usuario(@NotNull String usuario, @NotNull String senha) {

		this.usuario = usuario;
		this.senha = senha;
	}

	public Usuario(@NotNull String nome, @NotNull String usuario, @NotNull String senha) {

		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoDeUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeUsuario tipo) {
		this.tipo = tipo;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

}

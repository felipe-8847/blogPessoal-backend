package org.generation.blogPessoal.model.DTO;

import org.generation.blogPessoal.model.TipoDeUsuario;

public class UserLoginDTO {

	private String nome;
	private String usuario;
	private String senha;
	private String token;
	private String urlFoto;
	private TipoDeUsuario tipo;



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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public TipoDeUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeUsuario tipo) {
		this.tipo = tipo;
	}
	
}
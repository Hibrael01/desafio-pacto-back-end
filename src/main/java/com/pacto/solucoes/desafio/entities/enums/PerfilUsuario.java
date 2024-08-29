package com.pacto.solucoes.desafio.entities.enums;

public enum PerfilUsuario {

	ADMINISTRADORES("administradores"),
	COLABORADORES("colaboradores");
	
	private String perfil;

	PerfilUsuario(String perfil) {
		this.perfil = perfil;
	}
	
	public String getPerfil() {
		return perfil;
	}
	
}

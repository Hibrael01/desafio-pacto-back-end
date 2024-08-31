package com.pacto.solucoes.desafio.entities.DTO;

import com.pacto.solucoes.desafio.entities.enums.PerfilUsuario;

public record RegisterDTO(String nome, String email, String password, PerfilUsuario perfil) {

}

package com.pacto.solucoes.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.pacto.solucoes.desafio.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	UserDetails findByEmail(String email);

	Usuario findUsuarioByEmail(String email);
}

package com.pacto.solucoes.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pacto.solucoes.desafio.entities.Candidatura;
import com.pacto.solucoes.desafio.entities.Usuario;
import com.pacto.solucoes.desafio.entities.Vaga;

public interface CandidaturaRepository extends JpaRepository<Candidatura, String>{

	List<Candidatura> findByVagaAndUsuario(Vaga vaga, Usuario usuario);
	
}

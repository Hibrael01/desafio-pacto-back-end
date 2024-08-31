package com.pacto.solucoes.desafio.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pacto.solucoes.desafio.entities.Candidatura;
import com.pacto.solucoes.desafio.entities.Usuario;
import com.pacto.solucoes.desafio.entities.Vaga;
import com.pacto.solucoes.desafio.entities.enums.PerfilUsuario;
import com.pacto.solucoes.desafio.repository.CandidaturaRepository;
import com.pacto.solucoes.desafio.repository.UsuarioRepository;
import com.pacto.solucoes.desafio.repository.VagaRepository;

@Service
public class VagaService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private VagaRepository vagaRepository;
	
	@Autowired
	private CandidaturaRepository candidaturaRepository;
	
	public Vaga cadastrarVaga(Vaga vaga) throws Exception {
		try {
			
			if(vaga != null) {
				
				if(vaga.getUsuario() == null || StringUtils.isBlank(vaga.getUsuario().getId())) {
					throw new Exception("Usuário não identificado!");
				}
				
				if(StringUtils.isBlank(vaga.getTitulo())) {
					throw new Exception("Informe o Título da Vaga!");
				}
				
				if(StringUtils.isBlank(vaga.getDescricao())) {
					throw new Exception("Informe a Descrição para a Vaga!");
				}
				
				vaga.setDataCadastro(LocalDate.now());
				
				Vaga retornoVaga = new Vaga();
				
				retornoVaga = vagaRepository.save(vaga);
				
				if(retornoVaga != null && StringUtils.isNotBlank(retornoVaga.getId())) {
					return retornoVaga;
				}else {
					throw new Exception("Não foi possível cadastrar a nova Vaga!");
				}
				
			}
			
		} catch (Exception e) {
			throw e;
		}
		return null;
	}
	
	public List<Vaga> listarVagas() throws Exception {
		try {
			
			List<Vaga> lstVagas = new ArrayList<Vaga>();
			
			lstVagas = vagaRepository.findAll();
			
			if(lstVagas != null && !lstVagas.isEmpty()) {
				return lstVagas;
			}else {
				throw new Exception("Nenhuma vaga encontrada!");
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Candidatura candidatarNaVaga(Vaga vaga, Usuario candidato) throws Exception {
		try {
			
			if(vaga != null && candidato != null) {
				
				if(StringUtils.isNotBlank(vaga.getId()) && StringUtils.isNotBlank(candidato.getId())) {
					
					candidato = usuarioRepository.findById(candidato.getId()).get();
					
					if(candidato.getPerfil() != null && candidato.getPerfil() != PerfilUsuario.COLABORADORES) {
						throw new Exception("Somente usuários com perfil de colaboradores podem realizar candidaturas!");
					}
					
					//Verifica se candidato já se candidatou na vaga
					List<Candidatura> lstCandidaturasRealizadas = new ArrayList<Candidatura>();
					
					lstCandidaturasRealizadas = candidaturaRepository.findByVagaAndUsuario(vaga, candidato);
					
					if(lstCandidaturasRealizadas != null && !lstCandidaturasRealizadas.isEmpty()) {
						throw new Exception("Você já se candidatou nesta vaga!");
					}
					
					
					Candidatura candidatura = new Candidatura();
					candidatura.setUsuario(candidato);
					candidatura.setVaga(vaga);
					candidatura.setDataCandidatura(LocalDate.now());
					
					Candidatura retornoCandidatura = new Candidatura();
					
					retornoCandidatura = candidaturaRepository.save(candidatura);
					
					if(retornoCandidatura != null && StringUtils.isNotBlank(retornoCandidatura.getId())) {
						return retornoCandidatura;
					}else {
						throw new Exception("Não foi possível salvar a candidatura!");
					}
					
				}else {
					throw new Exception("Não foi possível prosseguir, informações incompletas!");
				}
				
			}
			
		} catch (Exception e) {
			throw e;
		}
		return null;
	}
	
}

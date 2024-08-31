package com.pacto.solucoes.desafio.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pacto.solucoes.desafio.entities.Candidatura;
import com.pacto.solucoes.desafio.entities.Vaga;
import com.pacto.solucoes.desafio.entities.DTO.CandidatarDTO;
import com.pacto.solucoes.desafio.service.VagaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vagas")
public class VagasController {
	
	@Autowired
	private VagaService vagaService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Object> cadastrarVaga(@RequestBody Vaga vaga) {
		
		try {
			
			if(vaga != null) {
				
				Vaga retornoVaga = new Vaga();
				
				retornoVaga = vagaService.cadastrarVaga(vaga);
				
				if(retornoVaga != null) {
					return ResponseEntity.ok(retornoVaga);
				}else {
					return ResponseEntity.badRequest().body("Não foi possível processar a ação!");
				}
				
			}else {
				return ResponseEntity.badRequest().body("Dados não informados, não é possível prosseguir!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@GetMapping
	public ResponseEntity<Object> listarVagas() {
		try {
			
			List<Vaga> lstVaga = new ArrayList<>();
			
			lstVaga = vagaService.listarVagas();
			
			if(lstVaga != null) {
				return ResponseEntity.ok(lstVaga);
			}else {
				return ResponseEntity.badRequest().body("Nenhuma vaga encontrada!");
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/candidatar")
	public ResponseEntity<Object> realizarCandidatura(@RequestBody CandidatarDTO candidatarDTO) {
		try {
			
			if(candidatarDTO != null) {
				
				if(candidatarDTO.vaga() != null && candidatarDTO.candidato() != null) {
					
					if(StringUtils.isBlank(candidatarDTO.vaga().getId())) {
						return ResponseEntity.badRequest().body("A vaga selecionada é inválida! Tente novamente.");
					}
					
					if(StringUtils.isBlank(candidatarDTO.candidato().getId())) {
						return ResponseEntity.badRequest().body("Não foi possível identificar o usuário, tente novamente!");
					}
					
					Candidatura retornoCandidatura = new Candidatura();
					
					retornoCandidatura = vagaService.candidatarNaVaga(candidatarDTO.vaga(), candidatarDTO.candidato());
					
					if(retornoCandidatura != null && StringUtils.isNotBlank(retornoCandidatura.getId())) {
						return ResponseEntity.ok(retornoCandidatura);
					}else {
						return ResponseEntity.badRequest().body("Não foi possível realizar a candidatura!");
					}
					
				}
				
			}
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}

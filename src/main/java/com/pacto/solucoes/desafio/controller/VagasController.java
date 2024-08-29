package com.pacto.solucoes.desafio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vagas")
public class VagasController {
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Object> cadastrarVaga(@RequestBody String descVaga) {
		
		try {
			
			return ResponseEntity.ok(descVaga);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@GetMapping
	public ResponseEntity<Object> listarVagas() {
		try {
			
			return ResponseEntity.ok("Tudo certo até aqui!");
					
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}

package com.pacto.solucoes.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pacto.solucoes.desafio.configuration.security.TokenService;
import com.pacto.solucoes.desafio.entities.Usuario;
import com.pacto.solucoes.desafio.entities.DTO.AuthenticationDTO;
import com.pacto.solucoes.desafio.entities.DTO.LoginResponseDTO;
import com.pacto.solucoes.desafio.entities.DTO.RegisterDTO;
import com.pacto.solucoes.desafio.repository.UsuarioRepository;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("pacto/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((Usuario) auth.getPrincipal());
		
		Usuario usuario = new Usuario();
		usuario = repository.findUsuarioByEmail(data.email());
		
		if(usuario != null) {
			LoginResponseDTO response = new LoginResponseDTO(token, usuario.getId());
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
		if(this.repository.findByEmail(data.email()) != null) {
			return ResponseEntity.badRequest().build();
		}
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		Usuario novoUsuario = new Usuario(data.nome(), data.email(), encryptedPassword, data.perfil());
		
		novoUsuario = this.repository.save(novoUsuario);
		
		novoUsuario.setPassword("");
		
		return ResponseEntity.ok(novoUsuario);
		
	}

}

package com.pacto.solucoes.desafio.configuration.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pacto.solucoes.desafio.entities.Usuario;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;
	
	//Método para gerar o token de autenticação
	public String generateToken(Usuario usuario) {
		try {
			
			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			String token = JWT.create()
					.withIssuer("login-auth-api")
					.withSubject(usuario.getEmail())
					.withExpiresAt(this.generateExpirationTime())
					.sign(algorithm);
			
			return token;
			
		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro durante a autenticacao");
		}
	}
	
	//Método para validar os tokens recebidos
	public String validateToken(String token) {
		try {
			
			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			return JWT.require(algorithm)
					.withIssuer("login-auth-api")
					.build()
					.verify(token)
					.getSubject();
			
		} catch (JWTVerificationException e) {
			return null;
		}
	}
	
	private Instant generateExpirationTime() {
		return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
	}
	
}

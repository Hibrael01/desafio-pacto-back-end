package com.pacto.solucoes.desafio.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pacto.solucoes.desafio.entities.enums.PerfilUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USUARIO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="ID")
	private String id;
	
	@Column(name="NOME", length = 60)
	private String nome;
	
	@Column(name="EMAIL", length = 90, nullable = false, unique = true)
	private String email;
	
	@Column(name="PASSWORD", length = 60, nullable = false)
	private String password;
	
	@Column(name = "PERFIL", nullable = false)
	private PerfilUsuario perfil;
	
	public Usuario(String nome, String email, String password, PerfilUsuario perfil) {
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.perfil = perfil;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.perfil == PerfilUsuario.ADMINISTRADORES) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		}else {
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		}
	}

	@Override
	public String getUsername() {
		return email;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	

}

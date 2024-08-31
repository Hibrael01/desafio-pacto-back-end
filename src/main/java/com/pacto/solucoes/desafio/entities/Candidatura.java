package com.pacto.solucoes.desafio.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CANDIDATURA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Candidatura {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "ID")
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "IDVAGA", nullable = false)
	private Vaga vaga;
	
	@ManyToOne
	@JoinColumn(name = "IDUSUARIO", nullable = false)
	private Usuario usuario;
	
	@Column(name = "DATA_CANDIDATURA")
	private LocalDate dataCandidatura;
	
}

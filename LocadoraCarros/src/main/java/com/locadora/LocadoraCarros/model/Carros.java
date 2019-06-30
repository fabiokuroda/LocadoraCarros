package com.locadora.LocadoraCarros.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
public class Carros {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_carro;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private String placa;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private String modelo;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private String categoria;
	
}

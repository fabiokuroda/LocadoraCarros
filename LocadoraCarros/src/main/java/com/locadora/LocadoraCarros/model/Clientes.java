package com.locadora.LocadoraCarros.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Clientes{

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_cliente;
	
	@Basic(optional = false)
	@Column(nullable = false)
	@CPF
	private String cpf_cliente;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private String nome_cliente;
	
//	@OneToOne
//	@JoinColumn(name = "cpf_cliente", insertable=false, updatable=false)
//	private Clientes clientes;
}

package com.locadora.LocadoraCarros.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
public class Locacao {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private int id_carro;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private int id_cliente;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private Date data_inicio;
	
	@Basic(optional = false)
	@Column(nullable = false)
	private Date data_fim;
	
	private int status;
	
	private BigDecimal valor;
	
	@OneToOne
	@JoinColumn(name = "id_carro", insertable=false, updatable=false)
    private Carros carros;
	@OneToOne
	@JoinColumn(name = "id_cliente", insertable=false, updatable=false)
    private Clientes clientes;

}

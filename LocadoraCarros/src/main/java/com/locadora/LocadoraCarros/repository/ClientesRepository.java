package com.locadora.LocadoraCarros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.locadora.LocadoraCarros.model.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Integer>{
	
	@Query(value="select count(id_cliente) from com.locadora.LocadoraCarros.model.Clientes where cpf_cliente = :cpf ")
	int existeCliente(@Param("cpf") String cpf_cliente);
	
}

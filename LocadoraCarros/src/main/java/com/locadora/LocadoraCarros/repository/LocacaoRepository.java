package com.locadora.LocadoraCarros.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.locadora.LocadoraCarros.model.Locacao;

public interface LocacaoRepository extends JpaRepository<Locacao, Integer>{
	
	@Query(value="select count(l.id) from com.locadora.LocadoraCarros.model.Clientes c inner join com.locadora.LocadoraCarros.model.Locacao l on "
			+ "(c.id_cliente = l.id_cliente and l.status = 1) where c.id_cliente = :id ")
	int existeClienteLocacao(@Param("id") int id_cliente);
	
	@Query(value="select count(l.id) from com.locadora.LocadoraCarros.model.Carros c inner join com.locadora.LocadoraCarros.model.Locacao l on "
			+ "(c.id_carro = l.id_carro and l.status = 1) where c.id_carro = :id ")
	int existeCarroLocacao(@Param("id") int id_carro);

	@Query(value="select count(id) from com.locadora.LocadoraCarros.model.Locacao where id = :id and status = 1")
	int locacaAtiva(@Param("id") int id_locacao);
	
	@Query(value="select data_fim from com.locadora.LocadoraCarros.model.Locacao where id_cliente = :id_cliente and status = 1")
	LocalDate getDataFimLocacao(@Param("id_cliente") int id_cliente);
}

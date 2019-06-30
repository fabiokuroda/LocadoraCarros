package com.locadora.LocadoraCarros.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.locadora.LocadoraCarros.model.Carros;

public interface CarrosRepository extends CrudRepository<Carros, Integer>{
	
	@Query(value="select count(id_carro) from com.locadora.LocadoraCarros.model.Carros where placa = :placa ")
	int existeCarro(@Param("placa") String placa);

}

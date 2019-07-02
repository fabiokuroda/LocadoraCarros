package com.locadora.LocadoraCarros.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.LocadoraCarros.model.Carros;
import com.locadora.LocadoraCarros.repository.CarrosRepository;

@RestController
@RequestMapping({"/api"})
public class CarrosController {
	@Autowired
	private CarrosRepository carrosRepository;
	
	@PostMapping("/carro")
	public Carros incluirCarro(@Valid @RequestBody Carros carro) {
		return carrosRepository.save(carro);
	}
	
	@GetMapping("/carro")
	public List<Carros> listarCarros() {
		return carrosRepository.findAll();
	}
	
	@GetMapping("/carro/{id_carro}")
	public Carros consultarCarro(@PathVariable("id_carro") Integer id_carro) {
		return carrosRepository.getOne(id_carro);
	}
	
	@PutMapping("/carro/{id_carro}")
	public Carros alterarCarro(@PathVariable("id_carro") Integer id_carro, @RequestBody Carros carro) {
		
		Carros carrosOri = carrosRepository.getOne(id_carro);
		
		carrosOri.setPlaca(carro.getPlaca());
		carrosOri.setModelo(carro.getModelo());
		carrosOri.setCategoria(carro.getCategoria());
		
	    return carrosRepository.save(carrosOri);
	}
	
	@DeleteMapping("/carro/{id_carro}")
    public List<Carros> delete(@PathVariable("id_carro") Integer id_carro) throws Exception
    {	
		try {
			carrosRepository.deleteById(id_carro);
		}catch(Throwable e) {
			throw new Exception("Não foi possível excluir carro.");
		}
        return carrosRepository.findAll();
    }

}
	
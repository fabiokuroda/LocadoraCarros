package com.locadora.LocadoraCarros.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.LocadoraCarros.model.Carros;
import com.locadora.LocadoraCarros.repository.CarrosRepository;

@RestController
@RequestMapping({"/carros"})
public class CarrosController {
	@Autowired
	private CarrosRepository carrosRepository;
	
	@SuppressWarnings("deprecation")
	@PostMapping("/incluirCarro")
	@ResponseBody
	public ResponseEntity<?> incluirCarro (@Valid @RequestBody Carros carro) {
		try {
			if (this.carrosRepository.existeCarro(carro.getPlaca()) != 0) {
				return new ResponseEntity<>("Carro já cadastrado.", HttpStatus.METHOD_FAILURE);
			} else {
				return new ResponseEntity<>(this.carrosRepository.save(carro), HttpStatus.OK);
			}
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarCarros")
	@ResponseBody
	public Iterable<Carros> listarCarros() {
		return carrosRepository.findAll();
	}
	
	@GetMapping("/consultarCarro")
	@ResponseBody
	public Optional<Carros> consultarCarro(@RequestParam Integer id_carro) {
		return carrosRepository.findById(id_carro);
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping("/alterarCarro")
	@ResponseBody
	public ResponseEntity<?> alterarCarro(@Valid @RequestBody Carros carro) {
		//Verifica se existe locação
		if (!carrosRepository.findById(carro.getId_carro()).isPresent()) {
			return new ResponseEntity<>("Carro não cadastrado.",HttpStatus.METHOD_FAILURE);
		}
		
		Carros carrosOri =
				carrosRepository
		            .findById(carro.getId_carro())
		            .orElseThrow(null);
		
		carrosOri.setPlaca(carro.getPlaca());
		carrosOri.setModelo(carro.getModelo());
		carrosOri.setCategoria(carro.getCategoria());
		
	    return new ResponseEntity<>(carrosRepository.save(carrosOri), HttpStatus.OK);
	}
}
	
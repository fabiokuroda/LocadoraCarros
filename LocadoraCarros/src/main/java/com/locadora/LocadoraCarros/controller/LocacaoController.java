package com.locadora.LocadoraCarros.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.LocadoraCarros.model.Locacao;
import com.locadora.LocadoraCarros.repository.CarrosRepository;
import com.locadora.LocadoraCarros.repository.ClientesRepository;
import com.locadora.LocadoraCarros.repository.LocacaoRepository;

@RestController
@RequestMapping({"/api"})
public class LocacaoController {
	@Autowired
	private LocacaoRepository locacaoRepository;
	
	@Autowired
	private CarrosRepository carrosRepository;
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	@SuppressWarnings("deprecation")
	@PostMapping("/locacao")
	public ResponseEntity<?> incluirLocacao(@Valid @RequestBody Locacao locacao) {
		//Verifica se existe carro cadastrado
		if (!carrosRepository.findById(locacao.getId_carro()).isPresent()) {
			return new ResponseEntity<>("Carro não cadastrado.",HttpStatus.METHOD_FAILURE);
		}
		
		//Verifica se existe cliente cadastrado
		if (!clientesRepository.findById(locacao.getId_cliente()).isPresent()) {
			return new ResponseEntity<>("Cliente não cadastrado.",HttpStatus.METHOD_FAILURE);
		}

		//Verifica se cliente está com carro alugado
		if(locacaoRepository.existeClienteLocacao(locacao.getId_cliente()) != 0) {
			return new ResponseEntity<>("Cliente possui carro alugado.",HttpStatus.METHOD_FAILURE);
		}
		
		//Verifica se carro está alugado
		if (locacaoRepository.existeCarroLocacao(locacao.getId_carro()) != 0) {
			return new ResponseEntity<>("Carro está alugado.",HttpStatus.METHOD_FAILURE);
		}
		
		//Verifica de data inicio é anterior a data atual
		if (locacao.getData_inicio().isBefore(LocalDate.now())) {
			return new ResponseEntity<>("Data inicio é anterior que a atual.", HttpStatus.METHOD_FAILURE);
		}

		//Verifica de data fim é anterior que data inicio
		if (locacao.getData_fim().isBefore(locacao.getData_inicio())) {
			return new ResponseEntity<>("Data final é anterior que a inicial.",HttpStatus.METHOD_FAILURE);
		}
		
		return new ResponseEntity<>(locacaoRepository.save(locacao), HttpStatus.OK);	
	}
	
	@GetMapping("/locacao/{idLocacao}")
	public Locacao consultarLocacao(@PathVariable("idLocacao") Integer idLocacao) {
		return locacaoRepository.getOne(idLocacao);
	}	
	
	@GetMapping("/locacao")
	public List<Locacao> listarLocacao() {
		return locacaoRepository.findAll();
	}	
	
	@SuppressWarnings("deprecation")
	@PutMapping("/locacao/{idLocacao}")
	public ResponseEntity<?> cancelarLocacao(@PathVariable("idLocacao") Integer idLocacao) {
		//Verifica se existe locação
		if (!locacaoRepository.findById(idLocacao).isPresent() || 
				locacaoRepository.locacaAtiva(idLocacao) == 0) {
			return new ResponseEntity<>("Locação não encontrada ou já cancelada.",HttpStatus.METHOD_FAILURE);
		}
		
		Locacao locacaoOri = locacaoRepository.getOne(idLocacao);
		
		locacaoOri.setStatus(0);
		
	    return new ResponseEntity<>(locacaoRepository.save(locacaoOri), HttpStatus.OK);
	}	
	
	@DeleteMapping("/locacao/{idLocacao}")
    public List<Locacao> delete(@PathVariable("idLocacao") Integer idLocacao)
    {
		locacaoRepository.deleteById(idLocacao);
        return locacaoRepository.findAll();
    }

	
}
	
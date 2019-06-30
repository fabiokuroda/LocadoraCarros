package com.locadora.LocadoraCarros.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.locadora.LocadoraCarros.model.Clientes;
import com.locadora.LocadoraCarros.repository.ClientesRepository;

@Controller
@RequestMapping({"/clientes"})
public class ClientesController {
	@Autowired
	private ClientesRepository clienteRepository;
	
	@SuppressWarnings("deprecation")
	@PostMapping("/incluirCliente")
	@ResponseBody
	public ResponseEntity<?> incluirCliente (@Valid @RequestBody Clientes cliente) {
		try {
			if (this.clienteRepository.existeCliente(cliente.getCpf_cliente()) != 0) {
				return new ResponseEntity<>("Cliente já cadastrado.",HttpStatus.METHOD_FAILURE);
			} else {
				return new ResponseEntity<>(this.clienteRepository.save(cliente),HttpStatus.OK);
			}
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarClientes")
	@ResponseBody
	public List<Clientes> listarClientes() {
		return (List<Clientes>) this.clienteRepository.findAll();
	}
	
	@GetMapping("/consultarCliente")
	@ResponseBody
	public Optional<Clientes> consultarCliente(@RequestParam Integer id_cliente) {
		return clienteRepository.findById(id_cliente);
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping("/alterarCliente")
	@ResponseBody
	public ResponseEntity<?> alterarCliente(@Valid @RequestBody Clientes cliente) {
		//Verifica se existe locação
		if (!clienteRepository.findById(cliente.getId_cliente()).isPresent()) {
			return new ResponseEntity<>("Cliente não cadastrado.",HttpStatus.METHOD_FAILURE);
		}
		
		Clientes clinteOri =
				clienteRepository
		            .findById(cliente.getId_cliente())
		            .orElseThrow(null);
		
		clinteOri.setCpf_cliente(cliente.getCpf_cliente());
		clinteOri.setNome_cliente(cliente.getNome_cliente());
		
	    return new ResponseEntity<>(clienteRepository.save(clinteOri), HttpStatus.OK);
	}	
}

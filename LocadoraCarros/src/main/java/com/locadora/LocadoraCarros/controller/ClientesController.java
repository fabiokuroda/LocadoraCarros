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

import com.locadora.LocadoraCarros.model.Clientes;
import com.locadora.LocadoraCarros.repository.ClientesRepository;

@RestController
@RequestMapping({"/api"})
public class ClientesController {
	@Autowired
	private ClientesRepository clienteRepository;
	
	@PostMapping("/cliente")
	public Clientes incluirCliente (@Valid @RequestBody Clientes cliente) {
		return clienteRepository.save(cliente);
	}
	
	@GetMapping("/cliente")
	public List<Clientes> listarClientes() {
		return (List<Clientes>) this.clienteRepository.findAll();
	}
	
	@GetMapping("/cliente/{id_cliente}")
	public Clientes consultarCliente(@PathVariable("id_cliente") Integer id_cliente) {
		return clienteRepository.getOne(id_cliente);
	}
	
	@PutMapping("/cliente/{id_cliente}")
	public Clientes alterarCliente(@PathVariable("id_cliente") Integer id_cliente, @RequestBody Clientes cliente) {
		
		Clientes clinteOri = clienteRepository.getOne(id_cliente);
		
		clinteOri.setCpf_cliente(cliente.getCpf_cliente());
		clinteOri.setNome_cliente(cliente.getNome_cliente());
		
	    return clienteRepository.save(clinteOri);
	}	
	
	@DeleteMapping("/cliente/{id_cliente}")
    public List<Clientes> delete(@PathVariable("id_cliente") Integer id_cliente) throws Exception
    {	
		try {
			clienteRepository.deleteById(id_cliente);
		}catch(Throwable e) {
			throw new Exception("Não foi possível excluir cliente.");
		}
        return clienteRepository.findAll();
    }

}

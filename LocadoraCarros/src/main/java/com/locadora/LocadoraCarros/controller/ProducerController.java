package com.locadora.LocadoraCarros.controller;

import java.time.LocalDate;
import java.time.Period;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.LocadoraCarros.model.Clientes;
import com.locadora.LocadoraCarros.model.Mensagem;
import com.locadora.LocadoraCarros.repository.ClientesRepository;
import com.locadora.LocadoraCarros.repository.LocacaoRepository;

@RestController
@RequestMapping({"/api"})
public class ProducerController {

	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private LocacaoRepository locacaoRepository;
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	@Autowired
    private Queue queue;
 
    private static final Logger logger = LoggerFactory.getLogger(ProducerController.class);

    
    public void send(Mensagem mensagem) {
    	logger.info("Enviando mensagem", mensagem);
        rabbitTemplate.convertAndSend(this.queue.getName(), mensagem);
    }
    
    @PostMapping("/calcular-dias-restantes")
	public int calcularDiasRestantes(@RequestParam Integer id_cliente) {
		LocalDate dataFim = locacaoRepository.getDataFimLocacao(id_cliente);
		
		Clientes cliente = clientesRepository.getOne(id_cliente);
		
		Period period = Period.between(LocalDate.now(), dataFim);
		
		int qtdDiasRestantes = period.getDays();
		
		Mensagem mensagem = new Mensagem(cliente.getNome_cliente(), qtdDiasRestantes);
		
		send(mensagem);
		
		return qtdDiasRestantes;
	}


}

package com.locadora.teste;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.locadora.LocadoraCarros.LocadoraCarrosApplication;
import com.locadora.LocadoraCarros.model.Carros;
import com.locadora.LocadoraCarros.model.Clientes;
import com.locadora.LocadoraCarros.model.Locacao;

@Rollback(true)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LocadoraCarrosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocadoraCarrosApplicationTests extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	private String getRootUrl() {
	return "http://localhost:" + port;
	}
	@Test
	public void contextLoads() {
	}
	
	@Test
	@Transactional
	public void testIncluirCliente() {
		Clientes cliente = new Clientes();
		cliente.setCpf_cliente("00000000191");
		cliente.setNome_cliente("João da Silva");
		ResponseEntity<Clientes> postResponse = restTemplate.postForEntity(getRootUrl() + "/clientes/incluirCliente", cliente, Clientes.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}
	
	@Test
	@Transactional
	public void testIncluirCarros() {
		Carros carros = new Carros();
		carros.setPlaca("CCC0000");
		carros.setModelo("Fiesta");
		carros.setModelo("EC");
		ResponseEntity<Carros> postResponse = restTemplate.postForEntity(getRootUrl() + "/carros/incluirCarro", carros, Carros.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}


	@Test
	@Transactional
	public void testIncluirLocacao() {
		Locacao locacao = new Locacao();
		locacao.setId_carro(1);
		locacao.setId_cliente(1);
		locacao.setData_inicio(new Date(2019,07,01));
		locacao.setData_fim(new Date(2019,07,05));
		locacao.setStatus(1);
		locacao.setValor(new BigDecimal("200"));
		ResponseEntity<Locacao> postResponse = restTemplate.postForEntity(getRootUrl() + "/locacao/incluirLocacao", locacao, Locacao.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}

}

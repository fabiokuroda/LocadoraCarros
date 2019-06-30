package com.locadora.teste;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.locadora.LocadoraCarros.model.Locacao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LocadoraCarrosApplicationTests.class)
@WebAppConfiguration
public class LocadoraCarrosApplicationTests extends AbstractApplicationTests{

	   @Override
	   @Before
	   public void setUp() {
	      super.setUp();
	   }
	   @Test
	   public void incluirLocacao() throws Exception {
		      String uri = "/locacao/incluirLocacao";
		      Locacao locacao = new Locacao();
		      locacao.setId_carro(1);
		      locacao.setId_cliente(1);
		      locacao.setData_inicio(new Date(2019,7,1));
		      locacao.setData_fim(new Date(2019,7,10));
		      locacao.setStatus(1);
		      locacao.setValor(new BigDecimal("1500"));
		      String inputJson = super.mapToJson(locacao);
		      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		      
		      int status = mvcResult.getResponse().getStatus();
		      assertEquals(201, status);
		      String content = mvcResult.getResponse().getContentAsString();
		      assertEquals(content, "Locação incluída com sucesso.");
	   }
	   
	   
	   @Test
	   public void consultarLocacao() throws Exception {
		      String uri = "/locacao/consultarLocacao";
		      Locacao locacao = new Locacao();
		      locacao.setId_carro(1);
		      locacao.setId_cliente(1);
		      locacao.setData_inicio(new Date(2019,7,1));
		      locacao.setData_fim(new Date(2019,7,10));
		      locacao.setStatus(1);
		      locacao.setValor(new BigDecimal("1500"));
		      String inputJson = super.mapToJson(locacao);
		      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		      
		      int status = mvcResult.getResponse().getStatus();
		      assertEquals(201, status);
		      String content = mvcResult.getResponse().getContentAsString();
		      assertEquals(content, "Locação incluída com sucesso.");
	   }
		 	 
}

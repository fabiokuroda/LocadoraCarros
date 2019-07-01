package com.locadora.LocadoraCarros;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LocadoraCarrosApplication {
	
	@Value("${queue.locadora.name}")
    private String locadoraQueue;

	public static void main(String[] args) {
		SpringApplication.run(LocadoraCarrosApplication.class, args);
	}

	@Bean
    public Queue queue() {
        return new Queue(locadoraQueue, true);
    }
	
}

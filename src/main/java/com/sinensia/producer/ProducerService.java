package com.sinensia.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

	@Value("${app.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${app.rabbitmq.routing-key}")
	private String routingKey;
	
	private final RabbitTemplate rabbitTemplate;

	public ProducerService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void enviarMensaje(String mensaje) {
		
		Producto producto = new Producto(System.currentTimeMillis(), mensaje, Math.round(Math.random()));
		
		rabbitTemplate.convertAndSend(exchange, routingKey, producto);
		
		System.out.println("Mensaje enviado: " + mensaje);
	}
	
}

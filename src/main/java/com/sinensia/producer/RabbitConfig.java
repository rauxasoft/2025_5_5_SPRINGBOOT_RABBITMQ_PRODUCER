package com.sinensia.producer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

	@Value("${app.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${app.rabbitmq.routing-key}")
	private String routingKey;
	
	@Bean
	Jackson2JsonMessageConverter messageConverter() {
	    return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	Queue queue() {
		return new Queue("cola.productos", true);
	}
	
	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange(exchange);
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
	}
}

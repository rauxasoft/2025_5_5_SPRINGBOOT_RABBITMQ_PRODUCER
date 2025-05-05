package com.sinensia.producer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerContoller {

	private final ProducerService producerService;
	
	public ProducerContoller(ProducerService producerService) {
		this.producerService = producerService;
	}
	
	@GetMapping("/producer")
	public String enviarMensaje(@RequestParam(required=false, defaultValue = "mensaje_por_defecto")  String mensaje) {
		this.producerService.enviarMensaje(mensaje);
		return "ok";
	}
}

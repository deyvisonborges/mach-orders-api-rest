package com.mach.machorderrestapi.app.api.order;

import com.mach.machorderrestapi.core.artifact.order.event.commands.CreateOrderCommand;
import com.mach.machorderrestapi.core.artifact.order.event.commands.CreateOrderCommandHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private final CreateOrderCommandHandler createOrderCommandHandler;

	public OrderController(CreateOrderCommandHandler createOrderCommandHandler) {
		this.createOrderCommandHandler = createOrderCommandHandler;
	}

	/*
	* Aqui eu posso também declarar um CommandResult como retorno
	* caso eu tenha cenários no qual eu preciso retornar alguma coisa.
	* O CQRS não necessariamente me obriga a ter retornos voids, mas o ideal
	* é garantir o que é escrita e o que é leitura.
	* */
	@GetMapping
	public void create(@RequestBody CreateOrderCommand command) {
		this.createOrderCommandHandler.execute(command);
	}
}

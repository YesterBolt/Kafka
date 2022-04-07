package br.com.curso.alura.api;

import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.curso.alura.dto.NewOrderRequest;
import br.com.curso.alura.service.MessageProducer;

@RestController
@RequestMapping("/v1/ecommerce")
public class ECommerceController {

    private MessageProducer messageProducerService;

    public ECommerceController(MessageProducer messageProducerService) {
        this.messageProducerService = messageProducerService;
    }

    @PostMapping("/produce/new-order")
    public void produceNewOrder(@RequestBody NewOrderRequest request) throws InterruptedException, ExecutionException {
        messageProducerService.produceNewMessage(request.getKey(), request.getValue());
    }
    
}

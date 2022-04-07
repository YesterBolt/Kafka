package br.com.curso.alura.service;

import java.util.concurrent.ExecutionException;

public interface MessageProducer {
    void produceNewMessage(String key, String message) throws InterruptedException, ExecutionException;
}

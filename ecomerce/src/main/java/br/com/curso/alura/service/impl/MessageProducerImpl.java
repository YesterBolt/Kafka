package br.com.curso.alura.service.impl;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import org.springframework.stereotype.Service;

import br.com.curso.alura.service.MessageProducer;

@Service
public class MessageProducerImpl implements MessageProducer {
    
    public void produceNewMessage(String key, String message) throws InterruptedException, ExecutionException {
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties());
        ProducerRecord<String, String> record = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", key, message);
        producer.send(record, (data, ex) -> {
            if(ex != null) {
                ex.printStackTrace();
                return;
            }
            System.out.println(data.topic() + "-->" + " Particao: " + data.partition() + " Offset: " + data.offset() + " Timestamp: " + data.timestamp());
        }).get();
    }

    private static Properties properties() {
        Properties properties = new Properties();

        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return properties;
    }
}

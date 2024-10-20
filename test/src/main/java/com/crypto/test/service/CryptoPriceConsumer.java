package com.crypto.test.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CryptoPriceConsumer {

    private String lastPrice = "Precio no disponible";

    @KafkaListener(topics = "crypto-price-topic", groupId = "gonza-group")
    public void consumePrice(String message) {
        lastPrice = message; // Actualiza el último precio
        System.out.println("Precio recibido desde Kafka: " + message); // Debug
    }

    public String getLastPrice() {
        return lastPrice; // Método para obtener el último precio
    }
}

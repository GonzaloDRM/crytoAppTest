package com.crypto.test.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CryptoPricePublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public CryptoPricePublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 5000)  // Ejecutar cada 5 segundos
    public String publishPrice() {
        String price = fetchCryptoPrice();
        kafkaTemplate.send("crypto-price-topic", price);
        return "Publicado el precio: " + price;
    }

    private String fetchCryptoPrice() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT"))
                .GET().build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error obteniendo precio";
        }
    }
}

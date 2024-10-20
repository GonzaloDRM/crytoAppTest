package com.crypto.test.service;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CryptoService {

    public void cryptoCall(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient httpClient = HttpClient.newHttpClient();
                HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT"))
                        .GET().build();

                HttpResponse<String> response = null;
                try {
                    response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                System.out.println(response.body());
            }
        });
        thread.start();
        System.out.println(thread.getName());
    }

}

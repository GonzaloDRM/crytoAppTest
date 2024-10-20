package com.crypto.test.controller;

import com.crypto.test.service.CryptoPricePublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CryptoController {

    private CryptoPricePublisher cryptoPricePublisher;

    public CryptoController(CryptoPricePublisher cryptoPricePublisher){
        this.cryptoPricePublisher = cryptoPricePublisher;
    }

    @GetMapping("/crypto")
    public ResponseEntity<?> getCrypto() throws InterruptedException {
        return ResponseEntity.ok().body(cryptoPricePublisher.publishPrice());
    }

}

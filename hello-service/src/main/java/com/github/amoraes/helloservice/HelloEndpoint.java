package com.github.amoraes.helloservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/greeting")
public class HelloEndpoint {

    @GetMapping
    public ResponseEntity<String> greeting(String name) {
        return ResponseEntity.ok(String.format("Hello %s", name));
    }

}

package com.boiko.user_service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class TestController {
    private final RestTemplate restTemplate;

    @GetMapping("/ids")
    public ResponseEntity<?> getAllIDs() {
        return restTemplate.getForEntity("http://MUSIC-SERVICE/api/v1/songs/ids", Long[].class);
    }
}

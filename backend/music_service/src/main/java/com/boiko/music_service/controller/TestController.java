package com.boiko.music_service.controller;

import com.boiko.music_service.model.Song;
import com.boiko.music_service.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/songs")
@RequiredArgsConstructor
public class TestController {
    private final SongRepository songRepository;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(songRepository.findAll());
    }

    @GetMapping("/ids")
    public ResponseEntity<?> getAllIDs() {
        return ResponseEntity.ok(
                songRepository.findAll()
                        .stream()
                        .map(Song::getId)
                        .toList()
        );
    }
}

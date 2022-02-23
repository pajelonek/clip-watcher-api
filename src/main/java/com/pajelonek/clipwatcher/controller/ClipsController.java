package com.pajelonek.clipwatcher.controller;

import com.pajelonek.clipwatcher.domain.twitch.ClipsRequest;
import com.pajelonek.clipwatcher.domain.twitch.ClipsResponse;
import com.pajelonek.clipwatcher.service.ClipsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClipsController {

    private final ClipsService clipsService;

    public ClipsController(ClipsService clipsService) {
        this.clipsService = clipsService;
    }

    @PostMapping(value = "/clips", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClipsResponse> clips(@RequestBody ClipsRequest clipsRequest) {
        return clipsService.clips(clipsRequest);
    }
}

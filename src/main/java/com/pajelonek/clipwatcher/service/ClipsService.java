package com.pajelonek.clipwatcher.service;

import com.pajelonek.clipwatcher.domain.twitch.ClipsRequest;
import com.pajelonek.clipwatcher.domain.twitch.ClipsResponse;
import com.pajelonek.clipwatcher.service.twitch.client.TwitchApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClipsService {

    private final TwitchApiClient twitchApiClient;

    public ClipsService(TwitchApiClient twitchApiClient) {
        this.twitchApiClient = twitchApiClient;
    }

    public ResponseEntity<ClipsResponse> clips(ClipsRequest clipsRequest) {
        log.info("Incoming POST request for /clips");
        ClipsResponse clipsResponse = twitchApiClient.clips(clipsRequest);
        return new ResponseEntity<>(clipsResponse, HttpStatus.OK);
    }

}
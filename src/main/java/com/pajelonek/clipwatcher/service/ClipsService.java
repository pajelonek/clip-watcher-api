package com.pajelonek.clipwatcher.service;

import com.pajelonek.clipwatcher.domain.twitch.ClipsRequest;
import com.pajelonek.clipwatcher.domain.twitch.ClipsResponse;
import com.pajelonek.clipwatcher.service.twitch.client.TwitchApiClient;
import com.pajelonek.clipwatcher.util.RequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClipsService {

    private final TwitchApiClient twitchApiClient;

    private final RequestValidator requestValidator;

    public ClipsService(TwitchApiClient twitchApiClient, RequestValidator requestValidator) {
        this.twitchApiClient = twitchApiClient;
        this.requestValidator = requestValidator;
    }

    public ResponseEntity<ClipsResponse> clips(ClipsRequest clipsRequest) {
        log.info("Incoming POST request for /clips with request: " + clipsRequest);
        requestValidator.validateClipsRequest(clipsRequest);
        ClipsResponse clipsResponse = twitchApiClient.clips(clipsRequest);
        return new ResponseEntity<>(clipsResponse, HttpStatus.OK);
    }

}

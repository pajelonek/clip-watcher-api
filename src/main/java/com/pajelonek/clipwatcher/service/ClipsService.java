package com.pajelonek.clipwatcher.service;

import com.pajelonek.clipwatcher.domain.twitch.clips.ClipsRequest;
import com.pajelonek.clipwatcher.domain.twitch.clips.ClipsResponse;
import com.pajelonek.clipwatcher.service.twitch.client.TwitchApiClient;
import com.pajelonek.clipwatcher.util.ClipsRequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record ClipsService(TwitchApiClient twitchApiClient, ClipsRequestValidator clipsRequestValidator) {

    public ResponseEntity<ClipsResponse> clips(ClipsRequest clipsRequest) {
        log.info("Incoming POST request for /clips with request: " + clipsRequest);
        clipsRequestValidator.validateClipsRequest(clipsRequest);
        ClipsResponse clipsResponse = twitchApiClient.clips(clipsRequest);
        return new ResponseEntity<>(clipsResponse, HttpStatus.OK);
    }

}

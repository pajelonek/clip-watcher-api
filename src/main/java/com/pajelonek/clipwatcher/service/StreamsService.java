package com.pajelonek.clipwatcher.service;

import com.pajelonek.clipwatcher.domain.twitch.streams.TopStreamsResponse;
import com.pajelonek.clipwatcher.service.twitch.client.TwitchApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record StreamsService(TwitchApiClient twitchApiClient) {

    public ResponseEntity<TopStreamsResponse> topStreams() {
        log.info("Incoming GET request for /top/games.");
        TopStreamsResponse topStreamsResponse = twitchApiClient.topStreams();
        return new ResponseEntity<>(topStreamsResponse, HttpStatus.OK);
    }

}

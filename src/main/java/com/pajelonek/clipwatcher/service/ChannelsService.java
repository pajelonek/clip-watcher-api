package com.pajelonek.clipwatcher.service;

import com.pajelonek.clipwatcher.domain.twitch.channels.ChannelsResponse;
import com.pajelonek.clipwatcher.service.twitch.client.TwitchApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChannelsService {

    private final TwitchApiClient twitchApiClient;

    public ChannelsService(TwitchApiClient twitchApiClient) {
        this.twitchApiClient = twitchApiClient;
    }

    public ResponseEntity<ChannelsResponse> searchChannel(String query) {
        log.info("Incoming GET request for /search/channels with query {}", query);
        ChannelsResponse categoriesResponse = twitchApiClient.searchChannels(query);
        return new ResponseEntity<>(categoriesResponse, HttpStatus.OK);
    }

}

package com.pajelonek.clipwatcher.service;

import com.pajelonek.clipwatcher.domain.twitch.channels.ChannelsResponse;
import com.pajelonek.clipwatcher.domain.twitch.common.Pagination;
import com.pajelonek.clipwatcher.service.twitch.client.TwitchApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public record ChannelsService(TwitchApiClient twitchApiClient) {

    public ResponseEntity<ChannelsResponse> searchChannel(String query) {
        log.info("Incoming GET request for /search/channels with query {}", query);
        if (query.isBlank() || query.isEmpty()) {
            log.info("Received empty query for /search/channels. Returning empty response");
            return new ResponseEntity<>(ChannelsResponse
                    .builder()
                    .data(new ArrayList<>())
                    .pagination(Pagination
                            .builder()
                            .cursor("")
                            .build())
                    .build(), HttpStatus.OK);
        }
        ChannelsResponse categoriesResponse = twitchApiClient.searchChannels(query);
        return new ResponseEntity<>(categoriesResponse, HttpStatus.OK);
    }

}

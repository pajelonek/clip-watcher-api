package com.pajelonek.clipwatcher.service.twitch.client;

import com.pajelonek.clipwatcher.domain.twitch.ClipsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Slf4j
public class TwitchApiUrlCreator {

    public static String createClipsUrl(ClipsRequest clipsRequest, String clipsEndpoint) {
        log.info("Creating url for /clips endpoint {} with request {}", clipsEndpoint, clipsRequest);
        return UriComponentsBuilder.fromHttpUrl(clipsEndpoint)
                .queryParamIfPresent("game_id", Optional.ofNullable(clipsRequest.getGameId()))
                .queryParamIfPresent("broadcaster_id", Optional.ofNullable(clipsRequest.getBroadcasterId()))
                .queryParamIfPresent("id", Optional.ofNullable(clipsRequest.getClipId()))
                .queryParamIfPresent("after", Optional.ofNullable(clipsRequest.getAfter()))
                .queryParamIfPresent("before", Optional.ofNullable(clipsRequest.getBefore()))
                .queryParamIfPresent("first", Optional.ofNullable(clipsRequest.getFirst()))
                .queryParamIfPresent("started_at", Optional.ofNullable(clipsRequest.getStartedAt()))
                .queryParamIfPresent("ended_at", Optional.ofNullable(clipsRequest.getEndedAt()))
                .toUriString();
    }
}
